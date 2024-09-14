$( document ).ready(function() {
    init();
});

function init(){
    $("#btnAddRepo").on('click',  {mode: 'ADD'}, openRepositoryAddEditPanel);

    $.ajax({
        url: "http://" + window.location.host + "/api/records",
        type: 'GET',
        success: function(res) {
            console.log(res);
            addRecordRows(res);

            //bind controls
            $(".btn-repo-add").on('click',  {mode: 'ADD'}, openRepositoryAddEditSecretPanel);
            $(".btn-repo-edit").on('click',  {mode: 'EDIT'}, openRepositoryAddEditPanel);
            $(".btn-repo-delete").on('click',  null, openRepositoryDeletePanel);

            $(".btn-repo-secret-check").on('click',  null, openRepositorySecretCheckPanel);
            $(".btn-repo-secret-edit").on('click',  {mode: 'EDIT'}, openRepositoryAddEditSecretPanel);
            $(".btn-repo-secret-delete").on('click',  null, openRepositorySecretDeletePanel);
        }
    });
}

function addRecordRows(records){

     let tBody = $('#tblRepo > tbody');
     let rows = "";

     $.each(records, function( index, record) {
        rows += generateRecordRow(record);
        $.each(record.secrets, function(j, secret){
            rows += generateSecretRow(record.id, secret);
        });
     });

     tBody.html(rows);
}

function addRecordRow(record){
    let tBody = $('#tblRepo > tbody');
    let rows = generateRecordRow(record);
    $.each(record.secrets, function(j, secret){
        rows += generateSecretRow(record.id, secret);
    });

    tBody.prepend(rows);
    let newRow = $('.tr-repo[repo-id="' + record.id + '"]');

    console.log(newRow);
    newRow.find(".btn-repo-add").on('click',  {mode: 'ADD'}, openRepositoryAddEditSecretPanel);
    newRow.find(".btn-repo-edit").on('click',  {mode: 'EDIT'}, openRepositoryAddEditPanel);
    newRow.find(".btn-repo-delete").on('click',  null, openRepositoryDeletePanel);
}

function updateRecordRow(record){
    let row = $('#tblRepo > tbody tr.tr-repo').filter('[repo-id="' + record.id + '"]');
    row.attr("repo-url", record.url);
    row.find("td.data").html(record.url);
}

function deleteRecordRow(record){
    let rows = $('#tblRepo > tbody tr').filter('[repo-id="' + record.id + '"]');
    rows.remove();
}

function deleteRecordSecretRow(secret){
    let repoSecretRow = $('#tblRepo > tbody tr.tr-repo-secret').filter('[repo-id="' + secret.recordId + '"][secret-key="' + secret.key + '"]');
    repoSecretRow.remove();
}

function addSecretRow(secret){
    console.log(secret);
    let repoRow = $('#tblRepo > tbody .tr-repo').filter('[repo-id="' + secret.recordId + '"]');
    let secretRow = generateSecretRow(secret.recordId, secret.key);
    repoRow.after(secretRow);

    let newRow = $('.tr-repo-secret[repo-id="' + secret.recordId + '"]');
     newRow.find(".btn-repo-secret-check").on('click',  null, openRepositorySecretCheckPanel);
     newRow.find(".btn-repo-secret-edit").on('click',  {mode: 'EDIT'}, openRepositoryAddEditSecretPanel);
     newRow.find(".btn-repo-secret-delete").on('click',  null, openRepositorySecretDeletePanel);

}

function generateRecordRow(record){
    return "<tr class='tr-repo' repo-id='" + record.id + "' repo-url='" +  record.url + "'>"
    + "<td class='data'>" + record.url + "</td>"
    + "<td class='ctrl'>"
    + "<a class='btn btn-repo-delete' title='Remove Repository'>&#128473;</a>"
    + "<a class='btn btn-repo-edit' title='Edit Repository'>&#128393;</a>"
    + "<a class='btn btn-repo-add' title='Add Secret'>&#10133;</a>"
    + "</td>"
    + "</tr>";
}

function generateSecretRow(recordId, secretKey){
    return "<tr class='tr-repo-secret' repo-id='" + recordId + "' secret-key='" + secretKey + "'>"
    + "<td class='data'>" + secretKey + "</td>"
    + "<td class='ctrl'>"
    + "<a class='btn btn-repo-secret-delete' title='Remove Secret'>&#128473;</a>"
    + "<a class='btn btn-repo-secret-edit' title='Edit Secret'>&#128393;</a>"
    + "<a class='btn btn-repo-secret-check' title='Check Secret'>&#x2714;</a>"
    + "</td>"
    + "</tr>";
}

function notifyRecordSecretCheck(secret, check){
    let repoSecretRow = $('#tblRepo > tbody tr.tr-repo-secret').filter('[repo-id="' + secret.recordId + '"][secret-key="' + secret.key + '"]');

    if(check){
        repoSecretRow.find("td").addClass('check-correct')
    }else{
        repoSecretRow.find("td").addClass('check-not-correct')
    }

    setTimeout(function(){
        repoSecretRow.find("td").removeClass('check-not-correct');
        repoSecretRow.find("td").removeClass('check-correct');

    }, 5000);

}

function openRepositoryAddEditPanel(e){
    e.preventDefault();

    let pnl = $("#pnlRepoAddEdit");
    let tr = $(e.target).parent().parent();

    //clear data
    pnl.find("#lblAction").html("");
    pnl.find("#txtRepoUrl").val("");
    pnl.find("#hdnRepoId").val(0);

    let method = "";
    let callback = null;
    //Set Mode
    if(e.data.mode == "ADD"){
        pnl.find("#lblAction").html("Add Repository");
        pnl.find("#hdnRepoId").val(0);
        method = "POST";
        callback = function(record){
            addRecordRow(record);
            $("#pnlRepoAddEdit .btn-close").click();
        }
    }else if(e.data.mode == "EDIT"){
        pnl.find("#lblAction").html("Edit Repository");
        pnl.find("#txtRepoUrl").val(tr.attr("repo-url"));
        pnl.find("#hdnRepoId").val(tr.attr("repo-id"));
        method = "PUT";
        callback = function(record){
            updateRecordRow(record);
            $("#pnlRepoAddEdit .btn-close").click();
        }
    }

    pnl.find(".btn-save").off('click');
    pnl.find(".btn-save").on(
        'click', {
            url: "http://" + window.location.host + "/api/records",
            method: method,
            getData: function(){
                let pnl = $("#pnlRepoAddEdit");
                let d = {
                    id: pnl.find("#hdnRepoId").val(),
                    url: pnl.find("#txtRepoUrl").val()
                };
                return d;
            },
            callback: callback
        },
        webRequest);

    $("#pnlRepoAddEdit").modal({
        escapeClose: false,
        clickClose: false,
        showClose: false
    });
}

function openRepositoryDeletePanel(e){
      e.preventDefault();
      let pnl = $("#pnlRepoDelete");
      let tr = $(e.target).parent().parent();

      pnl.find(".btn-ok").off('click');
      pnl.find(".btn-ok").on(
          'click', {
              url: "http://" + window.location.host + "/api/records/" + tr.attr("repo-id"),
              method: "DELETE",
              getData: function(){return null},
              callback: function(record){
                  deleteRecordRow(record);
                  $("#pnlRepoDelete .btn-close").click();
            }
          },
          webRequest);

      $("#pnlRepoDelete").modal({
          escapeClose: false,
          clickClose: false,
          showClose: false
      });

}


function openRepositoryAddEditSecretPanel(e){
    e.preventDefault();

    let pnl = $("#pnlRepoSecretAddEdit");
    let tr = $(e.target).parent().parent();

    //clear data
    pnl.find("#lblAction").html("");
    pnl.find("#txtRepoSecretKey").val("");
    pnl.find("#txtRepoSecretValue").val("");
    pnl.find("#hdnRepoId").val(0);

    //Set data
    let method = "";
    let callback = null;
    pnl.find("#hdnRepoId").val(tr.attr("repo-id"));

    if(e.data.mode == "ADD"){
        pnl.find("#lblAction").html("Add Secret");
        pnl.find("#txtRepoSecretKey").prop( "disabled", false );
        method = "POST";
        callback = function(secret){
            addSecretRow(secret);
            $("#pnlRepoSecretAddEdit .btn-close").click();
        }
    }else if(e.data.mode == "EDIT"){
        pnl.find("#lblAction").html("Edit Secret");
        pnl.find("#txtRepoSecretKey").prop( "disabled", true );
        method = "PUT";
        callback = function(secret){
             $("#pnlRepoSecretAddEdit .btn-close").click();
         }
    }

    pnl.find("#txtRepoSecretKey").val(tr.attr("secret-key"));
    pnl.find("#txtRepoSecretValue").val("");

    pnl.find(".btn-save").off('click');
    pnl.find(".btn-save").on(
        'click', {
            url: "http://" + window.location.host + "/api/secrets",
            method: method,
            getData: function(){
                let pnl = $("#pnlRepoSecretAddEdit");
                let d = {
                    recordId: pnl.find("#hdnRepoId").val(),
                    key: pnl.find("#txtRepoSecretKey").val(),
                    value: pnl.find("#txtRepoSecretValue").val()
                };
                return d;
            },
            callback: callback
        },
        webRequest);

    $("#pnlRepoSecretAddEdit").modal({
        escapeClose: false,
        clickClose: false,
        showClose: false
    });
}

function openRepositorySecretDeletePanel(e){
    e.preventDefault();
    let pnl = $("#pnlRepoSecretDelete");
    let tr = $(e.target).parent().parent();

    pnl.find("#hdnRepoId").val(tr.attr("repo-id"));
    pnl.find("#hdnRepoSecretKey").val(tr.attr("secret-key"));

    pnl.find(".btn-ok").off('click');
    pnl.find(".btn-ok").on(
        'click', {
            url: "http://" + window.location.host + "/api/secrets",
            method: "DELETE",
            getData: function(){
                let pnl = $("#pnlRepoSecretDelete");
                let d = {
                    recordId: pnl.find("#hdnRepoId").val(),
                    key: pnl.find("#hdnRepoSecretKey").val()
                };
                return d;
        },
        callback: function(secret){
            deleteRecordSecretRow(secret);
            $("#pnlRepoSecretDelete .btn-close").click();
        }
        },
        webRequest);

    $("#pnlRepoSecretDelete").modal({
        escapeClose: false,
        clickClose: false,
        showClose: false
    });
}

function openRepositorySecretCheckPanel(e){
    e.preventDefault();
    let pnl = $("#pnlRepoSecretCheck");
    let tr = $(e.target).parent().parent();

    pnl.find("#txtRepoSecretValue").val("");

    pnl.find("#hdnRepoId").val(tr.attr("repo-id"));
    pnl.find("#hdnRepoSecretKey").val(tr.attr("secret-key"));

    pnl.find(".btn-ok").off('click');
    pnl.find(".btn-ok").on(
        'click', {
            url: "http://" + window.location.host + "/api/secrets/check",
            method: "POST",
            getData: function(){
                let pnl = $("#pnlRepoSecretCheck");
                let d = {
                    recordId: pnl.find("#hdnRepoId").val(),
                    key: pnl.find("#hdnRepoSecretKey").val(),
                    value: pnl.find("#txtRepoSecretValue").val(),
                };
                return d;
            },
            callback: function(check){
                secret={
                    recordId: pnl.find("#hdnRepoId").val(),
                    key: pnl.find("#hdnRepoSecretKey").val()
                };
                notifyRecordSecretCheck(secret, check);
                $("#pnlRepoSecretCheck .btn-close").click();
            }
        },
        webRequest);

    $("#pnlRepoSecretCheck").modal({
        escapeClose: false,
        clickClose: false,
        showClose: false
    });
}

function openErrorPanel(error){

    $("#pnlError").modal({
        escapeClose: false,
        clickClose: false,
        showClose: false
    });
}

function webRequest(e){
    e.preventDefault();
    $.ajax({
        url: e.data.url,
        type: e.data.method,
        dataType: 'json',
        data: JSON.stringify(e.data.getData()),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success: e.data.callback
    }).fail(function(error){
        openErrorPanel(error);
    });
}
//window.location.host
