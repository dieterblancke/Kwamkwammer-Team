class Bootstrap {

    editableListManager;
    togetherTimeManager;

    init() {
        this.initEditableLists();
        this.initTogetherTimeManager();
    }

    initEditableLists() {
        this.editableListManager = new EditableListManager();
        this.editableListManager.init();
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

class EditableListManager {

    editableLists;

    init() {
        this.editableLists = [];
        const editableListElements = document.querySelectorAll(".movie-editable-list");

        for (let i = 0; i < editableListElements.length; i++) {
            const editableListElement = editableListElements[i];
            const baseBody = {
                movieType: editableListElement.getAttribute("data-movie-type")
            };

            this.editableLists.push(new EditableList('/watchlist/', baseBody, editableListElement));
        }
    }
}

class EditableList {

    baseEndpoint;
    baseBody;
    listElement;

    /**
     * @param {String} baseEndpoint
     * @param {Object} baseBody
     * @param {HTMLUListElement} editableListElement
     */
    constructor(baseEndpoint, baseBody, editableListElement) {
        this.baseEndpoint = baseEndpoint;
        this.baseBody = baseBody;
        this.listElement = editableListElement;
        const editableListLineItemElements = editableListElement.querySelectorAll("li");

        editableListLineItemElements.forEach(li => {
            this.initEventListeners(li, li.querySelector("input"));
        });
    }

    /**
     * @param {HTMLLIElement} li
     * @param {HTMLInputElement} input
     */
    initEventListeners(li, input) {
        const _this = this;

        li.addEventListener("click", _this.editItemEvent.bind(_this));
        input.addEventListener("blur", _this.blurInput.bind(_this));
        input.addEventListener("keydown", _this.keyInput.bind(_this));
    }

    /**
     * @param {MouseEvent} event
     */
    editItemEvent(event) {
        const currentTarget = event.currentTarget;

        this.editItem(currentTarget);
    }

    /**
     * @param {HTMLLIElement} element
     */
    editItem(element) {
        element.className = "edit";

        const inputField = element.querySelector("input");
        inputField.focus();
        inputField.setSelectionRange(0, inputField.value.length);
    }

    /**
     * @param {FocusEvent} event
     */
    blurInput(event) {
        const target = event.currentTarget;
        target.parentNode.className = "";

        if (target.value === "") {
            if (target.parentNode.getAttribute("data-new")) {
                this.addChild();
            }
            this.listElement.removeChild(target.parentNode);
        } else {
            target.previousElementSibling.innerHTML = target.value;

            if (target.parentNode.getAttribute("data-new")) {
                target.parentNode.removeAttribute("data-new");
                this.addChild();
            }
        }
    }

    /**
     * @param {KeyboardEvent} event
     */
    keyInput(event) {
        if (event.code === "Tab" || event.code === "Enter") {
            const target = event.currentTarget;

            event.preventDefault();
            target.blur();

            if (!target.getAttribute("data-new") && target.nextElementSibling != null) {
                this.editItem(target.nextElementSibling);
            }
        }
    }

    addChild() {
        const entry = document.createElement('li');
        entry.innerHTML = "<span>add another</span><input type='text'>";
        entry.setAttribute("data-new", "true");

        this.listElement.appendChild(entry);
        this.initEventListeners(entry, entry.querySelector("input"));

        console.log({
            ...this.baseBody,
            name: ''
        });

        fetch(this.baseEndpoint + 'create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                ...this.baseBody,
                name: ''
            })
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            console.log(json);
        });
    }
}

(function () {
    const bootstrap = new Bootstrap();

    document.addEventListener('DOMContentLoaded', bootstrap.init.bind(bootstrap));
})();