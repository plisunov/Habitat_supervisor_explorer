$(document).ready(function () {
    showEnvironment();
});

var reload = function () {
    showEnvironment();
}

var showEnvironment = function () {
    $.ajax({
        url: "get",
        type: "GET",
        complete: function (data) {
            if (data.status >= 200 && data.status < 300) {
                $("div.infostring").html("");
                showResults(data.responseJSON);
            } else {
                $("div.infostring").html("");
                var builderHTML = "<h4>" + data.responseText + "</h4>";
                $("div.infostring").html(builderHTML);
            }
        }
    });
}


function generateInfoFromMap(properties) {
    var result = "";
    Object.keys(properties).forEach(function (key) {
        var value = properties[key];
        if (typeof (value) === 'object') {
            result += "<li><b>" + key + "</b>: <ul>" + generateInfoFromMap(value) + "</ul></li>";
        } else {
            result += "<li><b>" + key + "</b>: " + properties[key] + "</li>";
        }

    });
    return result;
}

var showResults = function (listInfos) {
    $("div.resultInfo").html("");
    var builderHTML = "";
    builderHTML += "<table id=\"report\" style=\"width: 100%\">";
    builderHTML += "<tr>";
    builderHTML += "<th onclick='sortTable(\"name\")' style='cursor: pointer'>Service name</th>";
    builderHTML += "<th><input type='button' class='filter' onclick='openDialog(\"name\")'/></th>";
    builderHTML += "<th onclick='sortTable(\"group\")' style='cursor: pointer'>Service group</th>";
    builderHTML += "<th><input type='button' class='filter' onclick='openDialog(\"group\")'/></th>";
    builderHTML += "<th>Service version</th>";
    builderHTML += "<th onclick='sortTable(\"ip\")' style='cursor: pointer'>Service IP</th>";
    builderHTML += "<th><input type='button' class='filter' onclick='openDialog(\"ip\")'/></th>";
    builderHTML += "<th onclick='sortTable(\"host\")' style='cursor: pointer'>Service host</th>";
    builderHTML += "<th>Active<br></th>";

    builderHTML += "<th></th>";
    builderHTML += "</tr>";
    for (i = 0; i < listInfos.length; i++) {
        builderHTML += "<tr>";
        builderHTML += "<td colspan='2'>" + listInfos[i].serviceName + "</td>";
        builderHTML += "<td colspan='2'>" + listInfos[i].serviceGroup + "</td>";
        builderHTML += "<td>" + listInfos[i].pkg.version + "</td>";
        builderHTML += "<td colspan='2'><label>" + listInfos[i].system.ip + "</label> <div id=\"alert" + i + "\" class=\"alertDivHide\"><div>Copied to clipboard</div></div> <input type='button' class='copypaste' onclick='copytoClipboard(\"" + listInfos[i].system.ip + "\"," + i + ")'/></tdco>";
        builderHTML += "<td>" + listInfos[i].system.hostname + "</td>";
        builderHTML += "<td>" + listInfos[i].alive + "</td>";
        builderHTML += "<td><div class=\"arrow\"></div></td>";
        builderHTML += "</tr>";
        builderHTML += "<tr>"
        builderHTML += "<td colspan='10'>";
        builderHTML += "<li><b>Application</b>: " + listInfos[i].application + "</li>";
        builderHTML += "<li><b>Confirmed</b>: " + listInfos[i].confirmed + "</li>";
        builderHTML += "<li><b>Departed</b>: " + listInfos[i].departed + "</li>";
        builderHTML += "<li><b>Election is finished</b>: " + listInfos[i].electionIsFinished + "</li>";
        builderHTML += "<li><b>Election is no quorum</b>: " + listInfos[i].electionIsNoQuorum + "</li>";
        builderHTML += "<li><b>Election is running</b>: " + listInfos[i].electionIsRunning + "</li>";
        builderHTML += "<li><b>Environment</b>: " + listInfos[i].environment + "</li>";
        builderHTML += "<li><b>Follower</b>: " + listInfos[i].follower + "</li>";
        builderHTML += "<li><b>Leader</b>: " + listInfos[i].leader + "</li>";
        builderHTML += "<li><b>Member Id</b>: " + listInfos[i].memberId + "</li>";
        builderHTML += "<li><b>Package</b>: " + listInfos[i].packageString + "</li>";
        builderHTML += "<li><b>Persistent</b>: " + listInfos[i].persistent + "</li>";
        builderHTML += "<li><b>Suspect</b>: " + listInfos[i].suspect + "</li>";
        builderHTML += "<li><b>Update election is finished</b>: " + listInfos[i].updateElectionIsFinished + "</li>";
        builderHTML += "<li><b>Update election is no quorum</b>: " + listInfos[i].updateElectionIsNoQuorum + "</li>";
        builderHTML += "<li><b>Update election is running</b>: " + listInfos[i].updateElectionIsRunning + "</li>";
        builderHTML += "<li><b>Update follower</b>: " + listInfos[i].updateFollower + "</li>";
        builderHTML += "<li><b>Update leader</b>: " + listInfos[i].updateleader + "</li>";
        if (listInfos[i].configuration != null && !jQuery.isEmptyObject(listInfos[i].configuration)) {
            builderHTML += "<hr>";
            builderHTML += "<h4>Configuration</h4>";
            builderHTML += generateInfoFromMap(listInfos[i].configuration);
        }
        if (listInfos[i].properties != null) {
            builderHTML += "<hr>";
            builderHTML += "<h4>Properties</h4>";
            builderHTML += generateInfoFromMap(listInfos[i].properties);
        }
        builderHTML += "</td>";
        builderHTML += "</tr>";

    }
    builderHTML += "</table>";
    $("div.resultInfo").html(builderHTML);
    applyStyles();
}

var applyStyles = function () {

    $("#report tr:odd").addClass("odd");
    $("#report tr:not(.odd)").hide();
    $("#report tr:first-child").show();

    $("#report tr.odd").not($(this).find(".copypasteTD")).click(function () {
        $(this).next("tr").toggle();
        $(this).find(".arrow").toggleClass("up");
    });
}

var copytoClipboard = function (data, id) {
    var $temp = $("<input>");
    $("body").append($temp);
    $temp.val(data).select();
    document.execCommand("copy");
    $temp.remove();
    showAlert(id);
}

var sortTable = function (type) {
    $.ajax({
        url: "get",
        data: {type: type},
        type: "GET",
        complete: function (data) {
            if (data.status >= 200 && data.status < 300) {
                showResults(data.responseJSON);
            }
        }
    });
}

var filterTable = function (type) {
    var filterValue = $("#filterValue").get(0).value;
    $.ajax({
        url: "get",
        data: {filter: type,
               filterValue: filterValue},
        type: "GET",
        complete: function (data) {
            if (data.status >= 200 && data.status < 300) {
                showResults(data.responseJSON);
            }
        }
    });
}

var showAlert = function (id) {
    var alertDiv = $("#alert" + id);
    alertDiv.show();
    setTimeout(function () {
        alertDiv.hide();
    }, 1000);
}

var filterOnclick = function (filterType) {
    $("#dialog-form").hide();
    filterTable(filterType);
}


var openDialog = function (filterType) {
    document.getElementById( "filterOk" ).setAttribute( "onClick", "filterOnclick(\""+filterType+"\");" );
    $("#dialog-form").show();
    $("#filterValue").focus();
}

