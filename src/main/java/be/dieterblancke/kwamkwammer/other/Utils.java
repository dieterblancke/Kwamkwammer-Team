package be.dieterblancke.kwamkwammer.other;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils
{

    private static final LocalDate LOTTE_BIRTHDAY = LocalDate.of( Calendar.getInstance().get( Calendar.YEAR ), Month.AUGUST, 31 );
    private static final ThreadLocal<SimpleDateFormat> SIMPLE_DATE_FORMAT_THREAD_LOCAL = ThreadLocal.withInitial(
            () -> new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" )
    );
    private final static Pattern TIME_PATTERN = Pattern.compile( "(?:([0-9]+)\\s*y[a-z]*[,\\s]*)?"
                    + "(?:([0-9]+)\\s*mo[a-z]*[,\\s]*)?" + "(?:([0-9]+)\\s*w[a-z]*[,\\s]*)?" + "(?:([0-9]+)\\s*d[a-z]*[,\\s]*)?"
                    + "(?:([0-9]+)\\s*h[a-z]*[,\\s]*)?" + "(?:([0-9]+)\\s*m[a-z]*[,\\s]*)?" + "(?:([0-9]+)\\s*(?:s[a-z]*)?)?",
            Pattern.CASE_INSENSITIVE );

    public static String formatDate( final Date date )
    {
        return SIMPLE_DATE_FORMAT_THREAD_LOCAL.get().format( date );
    }

    public static long parseDateDiff( String time )
    {
        Matcher m = TIME_PATTERN.matcher( time );
        int years = 0, months = 0, weeks = 0, days = 0, hours = 0, minutes = 0, seconds = 0;
        boolean found = false;
        while ( m.find() )
        {
            if ( m.group() == null || m.group().isEmpty() )
            {
                continue;
            }
            for ( int i = 0; i < m.groupCount(); i++ )
            {
                if ( m.group( i ) != null && !m.group( i ).isEmpty() )
                {
                    found = true;
                    break;
                }
            }
            if ( found )
            {
                if ( m.group( 1 ) != null && !m.group( 1 ).isEmpty() )
                {
                    years = Integer.parseInt( m.group( 1 ) );
                }
                if ( m.group( 2 ) != null && !m.group( 2 ).isEmpty() )
                {
                    months = Integer.parseInt( m.group( 2 ) );
                }
                if ( m.group( 3 ) != null && !m.group( 3 ).isEmpty() )
                {
                    weeks = Integer.parseInt( m.group( 3 ) );
                }
                if ( m.group( 4 ) != null && !m.group( 4 ).isEmpty() )
                {
                    days = Integer.parseInt( m.group( 4 ) );
                }
                if ( m.group( 5 ) != null && !m.group( 5 ).isEmpty() )
                {
                    hours = Integer.parseInt( m.group( 5 ) );
                }
                if ( m.group( 6 ) != null && !m.group( 6 ).isEmpty() )
                {
                    minutes = Integer.parseInt( m.group( 6 ) );
                }
                if ( m.group( 7 ) != null && !m.group( 7 ).isEmpty() )
                {
                    seconds = Integer.parseInt( m.group( 7 ) );
                }
                break;
            }
        }
        if ( !found )
        {
            return 0;
        }
        if ( years > 20 )
        {
            return 0;
        }
        Calendar c = new GregorianCalendar();
        if ( years > 0 )
        {
            c.add( Calendar.YEAR, years );
        }
        if ( months > 0 )
        {
            c.add( Calendar.MONTH, months );
        }
        if ( weeks > 0 )
        {
            c.add( Calendar.WEEK_OF_YEAR, weeks );
        }
        if ( days > 0 )
        {
            c.add( Calendar.DAY_OF_MONTH, days );
        }
        if ( hours > 0 )
        {
            c.add( Calendar.HOUR_OF_DAY, hours );
        }
        if ( minutes > 0 )
        {
            c.add( Calendar.MINUTE, minutes );
        }
        if ( seconds > 0 )
        {
            c.add( Calendar.SECOND, seconds );
        }
        return c.getTimeInMillis();
    }

    public static boolean hasLottesBirthdayPassedYet() {
        return !LocalDate.now().isBefore( LOTTE_BIRTHDAY );
    }
}
