var modal;
var prev;
(function() {
    initializeModalBoxes();
    modal = document.getElementById('elementSelectionArea');
    var span = document.getElementById("closeModalBox");
    var documentBody = document.body;

    if (documentBody) {
        documentBody.addEventListener('mouseover', handler, false);
        documentBody.addEventListener('click', openModalBox);
    }
    else if (documentBody.attachEvent) {               // If browser doesn't support addEventListener
        documentBody.attachEvent('mouseover', function(e) {
            return handler(e || window.event);
        });
    }
    else {
        documentBody.onmouseover = handler;
    }

    function handler(event) {
        //      if (event.target === document.body ||
        //          (prev && prev === event.target)) {
        //          return;
        //      }
        if (prev) {
            prev.className = prev.className.replace(/\bhighlight\b/, '');
            prev = undefined;
        }
        if (event.target !== modal && !modal.contains(event.target)) {
            prev = event.target;
            prev.className += " highlight";
        }
    }

    function openModalBox(event) {
        var elementUnderInspection = event.target;
        if (event.target !== modal && !modal.contains(event.target)) {
            document.getElementById('htmlElementId').value = '';
            document.getElementById('htmlElementClass').value = '';
            document.getElementById('htmlElementName').value = '';
            //	clearModalTextInputs();
            var elementId = elementUnderInspection.id;
            var elementClass = elementUnderInspection.className.replace(/\bhighlight\b/, '').trim(); // replace given target's highlight class with none
            var elementName = elementUnderInspection.name;
            modal.style.display = "block";
            if (elementId != undefined) {
                document.getElementById('htmlElementId').value = elementId;
            }
            if (elementClass != undefined) {
                document.getElementById('htmlElementClass').value = elementClass;
            }
            if (elementName != undefined) {
                document.getElementById('htmlElementName').value = elementName;
            }
            if (elementUnderInspection == modal ) {
                modal.style.display = "none";
            }
        }
        if (elementUnderInspection == span) {
            modal.style.display = "none";
        }
    }

})();

function clearModalTextInputs() {   // teha hiljem rekursiivselt modaali lahtrite tühjendamine
    for(var i=0; i<modal.childNodes.length; i++) {   // Siin peab võtma modal child elemendi child elemendid
        var childElement = modal.childNodes[i];
        if (childElement.tagName) {
            if(childElement.type == 'text' && childElement.tagName.toLowerCase() == 'input') {
                childElement.value = '';
            }
        }
    }
}

function initializeModalBoxes() {
    var headTag = "document.getElementsByTagName('head')[0]";
    var body = "document.getElementsByTagName('body')[0]";
    var modalHtml = "<div id='someId' class='modal-content'><div class='model-header'><span id='closeModalBox' class='close'>&times;</span><h2>Element Inspector</h2></div>"+
        "<div class='modal-body'>" +
        "<table>" +
        "<tr><td><label for='htmlElementNamingDefined'>Element Name: </label></td><td><input type='text' name='htmlElementNamingDefined' id='htmlElementNamingDefined' /></td></tr>" +
        "<tr><td><label for='htmlElementId'>ID: </label></td><td><input type='text' name='htmlElementId' id='htmlElementId' /></td><td><button type='button' name='selectHtmlElementId' id='selectHtmlElementId' />Select</button></td></tr>"+
        "<tr><td><label for='htmlElementClass'>ClassName: </label></td><td><input type='text' name='htmlElementClass' id='htmlElementClass' /></td><td><button type='button' name='selectHtmlElementClass' id='selectHtmlElementClass' />Select</button></td></tr>"+
        "<tr><td><label for='htmlElementClass'>Name: </label></td><td><input type='text' name='htmlElementName' id='htmlElementName' /></td><td><button type='button' name='selecthtmlElementName' id='selecthtmlElementName' />Select</button></td></tr>"+
        "<tr><td><label for='htmlElementCssSelector'>CssSelector: </label></td><td><input type='text' name='htmlElementCssSelector' id='htmlElementCssSelector' /></td><td><button type='button' name='selectHtmlElementCssSelector' id='selectHtmlElementCssSelector' />Select</button></td></tr>" +
        "<tr><td><label for='htmlElementXPath'>XPath: </label></td><td><input type='text' name='htmlElementXPath' id='htmlElementXPath' /></td><td><button type='button' name='selectHtmlElementXPath' id='selectHtmlElementXPath' />Select</button></td></tr>" +
        "</table>" +
        "</div>" +
        "<div class='modal-footer'><h3>Modal Footer</h3></div></div>";
    var modalDiv = document.createElement('div');
    modalDiv.id = 'elementSelectionArea';
    modalDiv.className = 'modal';    // maybe it is needed better class name for uniqnuess
    modalDiv.innerHTML = modalHtml;

    document.body.appendChild(modalDiv);

    var modalCss = "/* The Modal (background) */\n" +
        ".modal {\n" +
        "    display: none; /* Hidden by default */\n" +
        "    position: fixed; /* Stay in place */\n" +
        "    z-index: 1; /* Sit on top */\n" +
        "    padding-top: 100px; /* Location of the box */\n" +
        "    left: 0;\n" +
        "    top: 0;\n" +
        "    width: 100%; /* Full width */\n" +
        "    height: 100%; /* Full height */\n" +
        "    overflow: auto; /* Enable scroll if needed */\n" +
        "    background-color: rgb(0,0,0); /* Fallback color */\n" +
        "    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */\n" +
        "}\n" +
        "\n" +
        "/* Modal Content */\n" +
        ".modal-content {\n" +
        "    position: relative;\n" +
        "    background-color: #fefefe;\n" +
        "    margin: auto;\n" +
        "    padding: 0;\n" +
        "    border: 1px solid #888;\n" +
        "    width: 80%;\n" +
        "    box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19);\n" +
        "    -webkit-animation-name: animatetop;\n" +
        "    -webkit-animation-duration: 0.4s;\n" +
        "    animation-name: animatetop;\n" +
        "    animation-duration: 0.4s\n" +
        "}\n" +
        "\n" +
        "/* Add Animation */\n" +
        "@-webkit-keyframes animatetop {\n" +
        "    from {top:-300px; opacity:0} \n" +
        "    to {top:0; opacity:1}\n" +
        "}\n" +
        "\n" +
        "@keyframes animatetop {\n" +
        "    from {top:-300px; opacity:0}\n" +
        "    to {top:0; opacity:1}\n" +
        "}\n" +
        "\n" +
        "/* The Close Button */\n" +
        ".close {\n" +
        "    color: white;\n" +
        "    float: right;\n" +
        "    font-size: 28px;\n" +
        "    font-weight: bold;\n" +
        "}\n" +
        "\n" +
        ".close:hover,\n" +
        ".close:focus {\n" +
        "    color: #000;\n" +
        "    text-decoration: none;\n" +
        "    cursor: pointer;\n" +
        "}\n" +
        "\n" +
        ".modal-header {\n" +
        "    padding: 2px 16px;\n" +
        "    background-color: #5cb85c;\n" +
        "    color: white;\n" +
        "}\n" +
        "\n" +
        ".modal-body {padding: 2px 16px;}\n" +
        "\n" +
        ".modal-footer {\n" +
        "    padding: 2px 16px;\n" +
        "    background-color: #5cb85c;\n" +
        "    color: white;\n" +
        "}";

    var modalStyle = document.createElement('style');
    modalStyle.type = 'text/css';
    modalStyle.appendChild(document.createTextNode(modalCss));
    document.head.appendChild(modalStyle);
}