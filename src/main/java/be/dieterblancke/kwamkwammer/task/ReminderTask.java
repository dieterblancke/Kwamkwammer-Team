package be.dieterblancke.kwamkwammer.task;

import be.dieterblancke.kwamkwammer.services.DiscordService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReminderTask
{

    private final DiscordService discordService;

    @Value( "${discord.guild.id}" )
    private String guildId;

    @Value( "${discord.channel.reminder.id}" )
    private String reminderChannelId;

    @Scheduled( cron = "0 0 9,16 * * *" )
    public void sendReminder()
    {
        final Guild guild = discordService.getJda().getGuildById( guildId );
        final TextChannel channel = guild.getTextChannelById( reminderChannelId );

        channel.getHistory().retrievePast( 10 ).queue( messages ->
        {
            if ( messages.size() == 1 )
            {
                channel.deleteMessageById( messages.get( 0 ).getId() ).queue();
            }
            else if ( messages.size() > 1 )
            {
                channel.deleteMessages( messages ).queue();
            }

            channel.sendMessage( "<@559653535713591296> <@183656254659362817>" )
                    .embed( new EmbedBuilder()
                            .setTitle( "Reminder for Dieter & Lotte" )
                            .addField(
                                    "",
                                    "- I, Dieter (non existant second name) Blancke hereby vow and promise that I will come to Lotte Elin van Overvest when I need her (emotional) help. This so, if she has to / wants to, she can always leave the call to have some her time knowing I can handle my own and will come to her if I cannot.",
                                    false
                            )
                            .addField(
                                    "",
                                    "- We will only join the call between 5.45 and 6.15 during work/week days so we can both have some us time and Lotte can relax and cool off a bit after getting home from school and Dieter from work.",
                                    false
                            )
                            .addField(
                                    "",
                                    "- We should both try to remain truthful to each other, even if we know it might hurt the other in the moment, because in the long run it’s better.",
                                    false
                            )
                            .addField(
                                    "",
                                    "- Weekends yet to be decided",
                                    false
                            )
                            .setFooter(
                                    "PS 1: Dieter should still mention when he closes Discord for no reason and should try to doomthink less.\n"
                                            + "PS 2: Lotte, don’t forget to catch the ropes."
                            )
                            .build()
                    )
                    .queue();
        } );
    }
}
