(function() {
    initializeModalBoxes();
    var prev;
    var pressedBoardKey;
    var elementId;
    var elementClass;
    var elementName;
    var elementCssSelector;
    var elementXPath;
    var elementTagName;
    var elementTagType;
    var modal = document.getElementById('elementSelectionArea');
    var span = document.getElementById("closeModalBox");
    var documentBody = document.body;

    if (documentBody) {
        documentBody.addEventListener('mouseover', handler, false);
        documentBody.addEventListener('click', handleMouseClickEvent, true);
        documentBody.addEventListener('keydown', handleKeyDownEvent)
    }
    else if (documentBody.attachEvent) {               // If browser doesn't support addEventListener
        documentBody.attachEvent('mouseover', function (e) {
            return handler(e || window.event);
        });
    }
    else {
        documentBody.onmouseover = handler;
    }

    window.onerror = function(msg, url, linenumber) {
        alert('Error message: '+msg+'\nURL: '+url+'\nLine Number: '+linenumber);
        return true;
    }

    /* The following code snippet is modified version from accepted answer here:
     https://stackoverflow.com/questions/11010569/highlight-a-dom-element-on-mouse-over-like-inspect-does */
    function handler(event) {
        if (prev) {
            prev.className = prev.className.replace(/\bhighlight\b/, '');
            prev = undefined;
        }
        if (event.target !== modal && !modal.contains(event.target)) {
            prev = event.target;
            prev.className += " highlight";
        }
    }

    function handleMouseClickEvent(e) {
        if (pressedBoardKey === 17 && e.which === 1) {   // if left CTRL and left mouse is clicked together
            e.preventDefault();
            e.stopImmediatePropagation();
            openModalBox(e);
            pressedBoardKey = undefined;
        }
    }

    function handleKeyDownEvent(e) {
        if (e.which == 17) {
            pressedBoardKey = 17;
        }
    }

    function openModalBox(event) {
        var elementUnderInspection = event.target;
        if (event.target !== modal && !modal.contains(event.target)) {
            clearModal();
            elementId = elementUnderInspection.id;
            elementClass = elementUnderInspection.className.replace(/\bhighlight\b/, '').trim(); // replace given target's highlight class with none
            elementName = elementUnderInspection.name;
            elementCssSelector = cssSelectorGenerator(elementUnderInspection);
            elementXPath = generateElementXPath(elementUnderInspection);
            elementTagName = elementUnderInspection.nodeName;
            elementTagType = elementUnderInspection.getAttribute("type");
            modal.style.display = "block";
            if (elementId != undefined) {
                document.getElementById('htmlElementId').innerHTML = elementId;
                document.getElementById('hiddenElementId').value = elementId;
            }
            if (elementClass != undefined) {
                document.getElementById('htmlElementClass').innerHTML = elementClass;
                document.getElementById('hiddenElementClass').value = elementClass;
            }
            if (elementName != undefined) {
                document.getElementById('htmlElementName').innerHTML = elementName;
                document.getElementById('hiddenElementNameAttr').value = elementName;
            }
            if (elementCssSelector != undefined) {
                document.getElementById('htmlElementCssSelector').innerHTML = elementCssSelector;
                document.getElementById('hiddenElementSelector').value = elementCssSelector;
            }
            if (elementXPath != undefined) {
                document.getElementById('htmlElementXPath').innerHTML = elementXPath;
                document.getElementById('hiddenElementXPath').value = elementXPath;
            }
            if (elementTagName != undefined) {
                document.getElementById('elementTagName').value = elementTagName;
            }
            if (elementTagType != undefined) {
                document.getElementById('elementTagType').value = elementTagType;
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

function createElementObject() {
    var elementId = document.getElementById('hiddenElementId').value;
    var elementClassName = document.getElementById('hiddenElementClass').value;
    var elementName = document.getElementById('hiddenElementNameAttr').value;
    var elementSelector = document.getElementById('hiddenElementSelector').value;
    var elementXPath = document.getElementById('hiddenElementXPath').value;
    var elementTagName = document.getElementById('elementTagName').value;
    var elementTagType = document.getElementById('elementTagType').value;
    var obj = '{'
        +'"id" : "' + elementId + '",'
        +'"className"  : "' + elementClassName + '",'
        +'"name" : "' + elementName + '",'
        +'"selector" : "' + elementSelector + '",'
        +'"xpath" : "' + elementXPath + '",'
        +'"tagName" : "' + elementTagName + '",'
        +'"tagType" : "' + elementTagType + '"'
        +'}';

    setTimeout(function() {
        // we use timeout here, because sometimes there is a bug when data is not sent to front-end, but little delay resolves it
        gateway.receiveUserSelection(obj);
    }, 500);
}

function closeModal() {
    document.getElementById('elementSelectionArea').style.display = "none";
}

function clearModal() {
    var modalChildInputs = document.getElementById('elementViewModalBox').querySelectorAll('input');
    var modalChildTds = document.getElementById('elementViewModalBox').querySelectorAll('.selectorInfoHolder');
    for(var i=0; i<modalChildInputs.length; i++) {
        modalChildInputs[i].value = '';
    }
    for(var j=0; j<modalChildTds.length; j++) {
        modalChildTds[j].innerHTML = '';
    }
}

function initializeModalBoxes() {
    var modalHtml = "<div id='elementViewModalBox' class='modal-content-main'>" +
        "<div class='modal-header-main'>" +
        "<button id='closeModalBox' onclick='closeModal()' class='close-modalbox-main'>&times;</button>" +
        "</div>"+
        "<div class='modal-body-main'>" +
        "<table>" +
        "<tr>" +
        "<td style='color: black'><label for='htmlElementId'>ID: </label></td>" +
        "<td style='color: black' name='htmlElementId' id='htmlElementId' class='selectorInfoHolder'></td>" +
        "</tr>"+
        "<tr>" +
        "<td style='color: black'><label for='htmlElementClass'>ClassName:</label></td>" +
        "<td style='color: black' name='htmlElementClass' id='htmlElementClass' class='selectorInfoHolder'></td>" +
        "</tr>"+
        "<tr>" +
        "<td style='color: black'><label for='htmlElementClass'>Name: </label></td>" +
        "<td style='color: black' name='htmlElementName' id='htmlElementName' class='selectorInfoHolder'></td>" +
        "</tr>"+
        "<tr>" +
        "<td style='color: black'><label for='htmlElementCssSelector'>CssSelector: </label></td>" +
        "<td style='color: black' name='htmlElementCssSelector' id='htmlElementCssSelector' class='selectorInfoHolder'></td>" +
        "</tr>" +
        "<tr>" +
        "<td style='color: black'><label for='htmlElementXPath'>XPath: </label></td>" +
        "<td style='color: black' name='htmlElementXPath' id='htmlElementXPath' class='selectorInfoHolder'></td>" +
        "</tr>" +
        "<tr>" +
        "<td><input type='hidden' name='hiddenElementId' id='hiddenElementId' /></td>" +
        "<td><input type='hidden' name='hiddenElementClass' id='hiddenElementClass' /></td>" +
        "<td><input type='hidden' name='hiddenElementNameAttr' id='hiddenElementNameAttr' /></td>" +
        "<td><input type='hidden' name='hiddenElementSelector' id='hiddenElementSelector' /></td>" +
        "<td><input type='hidden' name='hiddenElementXPath' id='hiddenElementXPath' /></td>" +
        "<td><input type='hidden' name='elementTagName' id='elementTagName' /></td>" +
        "<td><input type='hidden' name='elementTagType' id='elementTagType' /></td>" +
        "</tr>" +
        "<tr>" +
        "<td><button class='inspectionButton' onclick='createElementObject()'>Select</button></td>" +
        "</tr>" +
        "</table>" +
        "</div>" +
        "<div class='modal-footer-main'></div></div>";
    var modalDiv = document.createElement('div');
    modalDiv.id = 'elementSelectionArea';
    modalDiv.className = 'modal-elementPicker';    // maybe it is needed better class name for uniqnuess
    modalDiv.innerHTML = modalHtml;

    document.body.appendChild(modalDiv);


    var modalCss = "/* The Modal (background) */\n" +
        ".modal-elementPicker {\n" +
        "    display: none; /* Hidden by default */\n" +
        "    position: fixed; /* Stay in place */\n" +
        "    z-index: 9999; /* Sit on top */\n" +
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
        ".modal-content-main {\n" +
        "    position: relative;\n" +
        "    background-color: #e8edf2;\n" +
        "    overflow:hidden;\n" +
        "    margin: auto;\n" +
        "    padding: 0;\n" +
        "    border: 1px solid #888;\n" +
        "    width: 40%;\n" +
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
        ".close-modalbox-main {\n" +
        "    color: white;\n" +
        "    float: right;\n" +
        "    font-size: 14px;\n" +
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
        ".modal-header-main {\n" +
        "    padding: 2px 16px;\n" +
        "    height: 30px;\n" +
        "    background-color: #9daab7;\n" +
        "    color: white;\n" +
        "}\n" +
        "\n" +
        ".modal-body-main {padding: 2px 16px;}\n" +
        "\n" +
        ".modal-footer-main {\n" +
        "    padding: 2px 16px;\n" +
        "    height: 30px; \n" +
        "    background-color: #9daab7;\n" +
        "    color: white;\n" +
        "} \n" +
        ".inspectionButton {\n" +
        "    background: #e8eaeb; \n" +
        "    background-image: -webkit-linear-gradient(top, #e8eaeb, #82888c); \n" +
        "    background-image: -moz-linear-gradient(top, #e8eaeb, #82888c); \n" +
        "    background-image: -ms-linear-gradient(top, #e8eaeb, #82888c); \n" +
        "    background-image: -o-linear-gradient(top, #e8eaeb, #82888c); \n" +
        "    background-image: linear-gradient(to bottom, #e8eaeb, #82888c); \n" +
        "    -webkit-border-radius: 13; \n" +
        "    -moz-border-radius: 13; \n" +
        "    border-radius: 13px; \n" +
        "    font-family: Arial; \n" +
        "    color: #080108; \n" +
        "    font-size: 15px; \n" +
        "    padding: 7px 15px 7px 15px; \n" +
        "    text-decoration: none; \n" +
        "} \n" +
        ".inspectionButton:hover { \n" +
        "    background: #000000; \n" +
        "    background-image: -webkit-linear-gradient(top, #828a8f, #92999e); \n" +
        "    background-image: -moz-linear-gradient(top, #828a8f, #92999e); \n" +
        "    background-image: -ms-linear-gradient(top, #828a8f, #92999e); \n" +
        "    background-image: -o-linear-gradient(top, #828a8f, #92999e); \n" +
        "    background-image: linear-gradient(to bottom, #828a8f, #92999e); \n" +
        "    text-decoration: none; \n" +
        "}";

    var modalStyle = document.createElement('style');
    modalStyle.type = 'text/css';
    modalStyle.appendChild(document.createTextNode(modalCss));
    document.head.appendChild(modalStyle);
}

function cssSelectorGenerator(element) {
    var selectorParts = [];
    while (element.parentNode) {
        if (element.id && isElementIdUnique(element.id)) {
            var elementId = element.id.toString();
            if (elementId.indexOf('.') !== -1) {
                elementId = elementId.replace(/\./g, "\\\\2e ")    // Selector does not recognize . in id, so it must be replaced with \\2e
            }
            selectorParts.unshift('#'+elementId);
            break;
        }
        var elementParentNodeChildren = element.parentNode.childNodes;
        var sameTagsUnderParentElement = findSameTagOccuranceUnderParentNode(elementParentNodeChildren, element); // we use nth-type child for same tags
        var elementTagIndexer = 1;  // If parentNode has multiple tags that target element has, we start indexing
        for(var i=0; i<elementParentNodeChildren.length; i++) {
            if (element === elementParentNodeChildren[i]) {
                if (element.name) {
                    selectorParts.unshift(element.tagName.toLowerCase() + "[name='" + element.name + "']");
                } else {
                    if (sameTagsUnderParentElement == 1) selectorParts.unshift(element.tagName.toLowerCase());
                    else selectorParts.unshift(element.tagName.toLowerCase()
                            + ':nth-of-type(' + elementTagIndexer + ')');
                }
                break;
            } else {
                if (element.tagName === elementParentNodeChildren[i].tagName
                    && elementParentNodeChildren[i].nodeType === 1) elementTagIndexer++;
            }
        }
        element = element.parentNode;
    }
    return selectorParts.join(' > ');
}

function findSameTagOccuranceUnderParentNode(parentChildren, targetElement) {
    var elementOccuranceCounter = 0;
    for(var i = 0; i < parentChildren.length; i++) {
        if (parentChildren[i].tagName === targetElement.tagName) elementOccuranceCounter++;
    }
    return elementOccuranceCounter;
}

function generateElementXPath(element) {
    var elementTagName = element.tagName.toLowerCase();
    if (element.id && isElementIdUnique(element.id)) {
        return '//' + elementTagName + '[@id=\'' + element.id + '\']';
    } else if (element.name &&
        isElementNameUnique(element.name)) {
        return '//' + elementTagName + '[@name=\'' + element.getAttribute("name") + '\']';
    }

    if (element === document.body) return '//' +  element.tagName.toLowerCase();

    var children = element.parentNode.childNodes;
    var targetElementSameTagNameCount = countTargetElementTagNameCount(children, element);

    if (targetElementSameTagNameCount[1] > 1) {
        return generateElementXPath(element.parentNode) + '/' + element.tagName.toLowerCase() + '['+(targetElementSameTagNameCount[0])+']';
    } else {
        return generateElementXPath(element.parentNode) + '/' + element.tagName.toLowerCase()
    }
}

function isElementIdUnique(id) {
    if(document.querySelectorAll('[id="' + id + '"]').length > 1) {
        return false;
    }
    return true;
}

function isElementNameUnique(name) {
    if(document.querySelectorAll('[name="' + name + '"]').length > 1) return false;
    return true;
}

function countTargetElementTagNameCount(children, element) {
    var targetElementTagCount = 0;
    var targetElementIndex = 0;
    for (var i = 0; i < children.length; i++) {
        if (children[i] === element) {
            targetElementTagCount++;
            targetElementIndex = targetElementTagCount;
        } else if (children[i].tagName === element.tagName && children[i].nodeType === 1) {
            targetElementTagCount++;
        }
    }
    return [targetElementIndex, targetElementTagCount];
}