(function () {
    const languages = [];

    document.addEventListener("DOMContentLoaded", () => {
        document.querySelectorAll(".multi-language").forEach(initMultiLanguage);
    });

    function initMultiLanguage(element) {
        element.querySelectorAll("p[data-language]").forEach(p => {
            languages.push({
                element: p,
                language: p.getAttribute("data-language"),
                selected: p.classList.contains("default"),
            });
        });

        const languageSelector = document.createElement("ul");
        languageSelector.classList.add("language-selector");

        for (let language of languages) {
            const li = document.createElement("li");

            li.innerText = language.language;
            language.selectorElement = li;
            li.addEventListener("click", () => {
                for (let l of languages) {
                    l.selected = false;
                }

                language.selected = true;
                updateTextView();
            });
            languageSelector.appendChild(li);
        }

        updateTextView();

        element.appendChild(languageSelector);
    }

    function updateTextView() {
        for (let language of languages) {
            if (language.selected) {
                language.element.setAttribute("style", "");

                if (language.selectorElement) {
                    language.selectorElement.setAttribute("style", "color: #C25964");
                }
            } else {
                language.element.setAttribute("style", "display: none;");

                if (language.selectorElement) {
                    language.selectorElement.setAttribute("style", "");
                }
            }
        }
    }
}());