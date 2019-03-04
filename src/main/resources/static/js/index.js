$( document ).ready(function() {
    showEnvironment();
});


var showEnvironment = function () {
    $.ajax({
        url: "get",
        type: "GET",
        complete: function (data) {
            if (data.status >= 200 && data.status < 300) {
                showResults(data.responseJSON);
            }
        }
    });
}


var showResults = function (listInfos) {
    $("div.resultInfo").html("");
    var builderHTML = "";
    builderHTML += "<table id=\"report\">";
    builderHTML += "<tr>";
    builderHTML += "<th onclick='sortTable(\"name\")'><p>Service name</p></th>";
    builderHTML += "<th onclick='sortTable(\"group\")'><p>Service group</p></th>";
    builderHTML += "<th>Service version</th>";
    builderHTML += "<th onclick='sortTable(\"ip\")'><p>Service IP</p></th>";
    builderHTML += "<th onclick='sortTable(\"host\")'><p>Service host</p></th>";
    builderHTML += "<th>Active</th>";

    builderHTML += "<th></th>";
    builderHTML += "</tr>";
    for (i = 0; i < listInfos.length; i++) {
        builderHTML += "<tr>";
        builderHTML += "<td>" + listInfos[i].serviceName + "</td>";
        builderHTML += "<td>" + listInfos[i].serviceGroup + "</td>";
        builderHTML += "<td>" + listInfos[i].pkg.version + "</td>";
        builderHTML += "<td>" + listInfos[i].system.ip + "</td>";
        builderHTML += "<td>" + listInfos[i].system.hostname + "</td>";
        builderHTML += "<td>" + listInfos[i].alive + "</td>";
        builderHTML += "<td><div class=\"arrow\"></div></td>";
        builderHTML += "</tr>";
        builderHTML += "<tr>"
        builderHTML += "<td colspan=\"7\">";
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

    $("#report tr.odd").click(function () {
        $(this).next("tr").toggle();
        $(this).find(".arrow").toggleClass("up");
    });
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
