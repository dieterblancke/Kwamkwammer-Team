class Bootstrap {

    togetherTimeManager;

    init() {
        this.initTogetherTimeManager();
    }

    initTogetherTimeManager() {
        this.togetherTimeManager = new TogetherTimeManager();
        this.togetherTimeManager.init();
    }
}

class TogetherTimeManager {

    timeOfBecomingCouple;

    init() {
        this.timeOfBecomingCouple = 1623365100000;

        setInterval(this.update.bind(this), 1000);
    }

    update() {
        document.querySelectorAll(".together-display").forEach(element => {
            let delta = Math.abs(new Date().getTime() - this.timeOfBecomingCouple) / 1000;

            const years = Math.floor(delta / 31536000);
            delta -= years * 31536000;

            const days = Math.floor(delta / 86400);
            delta -= days * 86400;

            const hours = Math.floor(delta / 3600) % 24;
            delta -= hours * 3600;

            const minutes = Math.floor(delta / 60) % 60;
            delta -= minutes * 60;

            const seconds = Math.floor(delta % 60);

            element.innerHTML = `${this.pluralize("year", years)} ${this.pluralize("day", days)} ${this.pluralize("hour", hours)} ${this.pluralize("minute", minutes)} ${this.pluralize("second", seconds)}`;
        });
    }

    pluralize(word, amount) {
        return amount + " " + word + (amount === 1 ? "" : "s");
    }
}

(function () {
    const bootstrap = new Bootstrap();

    document.addEventListener('DOMContentLoaded', bootstrap.init.bind(bootstrap));
})();