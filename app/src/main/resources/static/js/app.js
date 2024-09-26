$( document ).ready(function() {
    init();
});

function init(){
    $("#btnAddRecord").on('click',  {mode: 'ADD'}, openRecordAddEditPanel);

    $.ajax({
        url: "http://" + window.location.host + "/api/records",
        type: 'GET',
        success: function(res) {
            addRecordRows(res);

            $(".btn-repo-add").on('click',  {mode: 'ADD'}, openRecordAddEditSecretPanel);
            $(".btn-repo-edit").on('click',  {mode: 'EDIT'}, openRecordAddEditPanel);
            $(".btn-repo-delete").on('click',  null, openRecordDeletePanel);

            $(".btn-repo-secret-check").on('click',  null, openRecordSecretCheckPanel);
            $(".btn-repo-secret-edit").on('click',  {mode: 'EDIT'}, openRecordAddEditSecretPanel);
            $(".btn-repo-secret-delete").on('click',  null, openRecordSecretDeletePanel);
        }
    });
}

function addRecordRows(records){

     let tBody = $('#tblRecord > tbody');
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
    let tBody = $('#tblRecord > tbody');
    let rows = generateRecordRow(record);
    $.each(record.secrets, function(j, secret){
        rows += generateSecretRow(record.id, secret);
    });

    tBody.prepend(rows);
    let newRow = $('.tr-repo[repo-id="' + record.id + '"]');

    newRow.find(".btn-repo-add").on('click',  {mode: 'ADD'}, openRecordAddEditSecretPanel);
    newRow.find(".btn-repo-edit").on('click',  {mode: 'EDIT'}, openRecordAddEditPanel);
    newRow.find(".btn-repo-delete").on('click',  null, openRecordDeletePanel);
}

function updateRecordRow(record){
    let row = $('#tblRecord > tbody tr.tr-repo').filter('[repo-id="' + record.id + '"]');
    row.attr("repo-url", record.url);
    row.find("td.data").html(record.url);
}

function deleteRecordRow(record){
    let rows = $('#tblRecord > tbody tr').filter('[repo-id="' + record.id + '"]');
    rows.remove();
}

function deleteRecordSecretRow(secret){
    let repoSecretRow = $('#tblRecord > tbody tr.tr-repo-secret').filter('[repo-id="' + secret.recordId + '"][secret-key="' + secret.key + '"]');
    repoSecretRow.remove();
}

function addSecretRow(secret){
    let repoRow = $('#tblRecord > tbody .tr-repo').filter('[repo-id="' + secret.recordId + '"]');
    let secretRow = generateSecretRow(secret.recordId, secret.key);
    repoRow.after(secretRow);

    let newRow = $('.tr-repo-secret[repo-id="' + secret.recordId + '"]');
     newRow.find(".btn-repo-secret-check").on('click',  null, openRecordSecretCheckPanel);
     newRow.find(".btn-repo-secret-edit").on('click',  {mode: 'EDIT'}, openRecordAddEditSecretPanel);
     newRow.find(".btn-repo-secret-delete").on('click',  null, openRecordSecretDeletePanel);
}

function generateRecordRow(record){
    return "<tr class='tr-repo' repo-id='" + record.id + "' repo-url='" +  record.url + "'>"
    + "<td class='data'>" + record.url + "</td>"
    + "<td class='ctrl'>"
    + "<a class='btn btn-repo-delete' title='Remove Record'>&#128473;</a>"
    + "<a class='btn btn-repo-edit' title='Edit Record'>&#128393;</a>"
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
    let repoSecretRow = $('#tblRecord > tbody tr.tr-repo-secret').filter('[repo-id="' + secret.recordId + '"][secret-key="' + secret.key + '"]');

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

function openRecordAddEditPanel(e){
    e.preventDefault();

    let pnl = $("#pnlRecordAddEdit");
    let tr = $(e.target).parent().parent();

    //clear data
    pnl.find("#lblAction").html("");
    pnl.find("#txtRecordUrl").val("");
    pnl.find("#hdnRecordId").val(0);

    let method = "";
    let callback = null;
    //Set Mode
    if(e.data.mode == "ADD"){
        pnl.find("#lblAction").html("Add Record");
        pnl.find("#hdnRecordId").val(0);
        method = "POST";
        callback = function(record){
            addRecordRow(record);
            $("#pnlRecordAddEdit .btn-close").click();
        }
    }else if(e.data.mode == "EDIT"){
        pnl.find("#lblAction").html("Edit Record");
        pnl.find("#txtRecordUrl").val(tr.attr("repo-url"));
        pnl.find("#hdnRecordId").val(tr.attr("repo-id"));
        method = "PUT";
        callback = function(record){
            updateRecordRow(record);
            $("#pnlRecordAddEdit .btn-close").click();
        }
    }

    pnl.find(".btn-save").off('click');
    pnl.find(".btn-save").on(
        'click', {
            url: "http://" + window.location.host + "/api/records",
            method: method,
            getData: function(){
                let pnl = $("#pnlRecordAddEdit");
                let d = {
                    id: pnl.find("#hdnRecordId").val(),
                    url: pnl.find("#txtRecordUrl").val()
                };
                return d;
            },
            callback: callback
        },
        webRequest);

    $("#pnlRecordAddEdit").modal({
        escapeClose: false,
        clickClose: false,
        showClose: false
    });
}

function openRecordDeletePanel(e){
      e.preventDefault();
      let pnl = $("#pnlRecordDelete");
      let tr = $(e.target).parent().parent();

      pnl.find(".btn-ok").off('click');
      pnl.find(".btn-ok").on(
          'click', {
              url: "http://" + window.location.host + "/api/records/" + tr.attr("repo-id"),
              method: "DELETE",
              getData: function(){return null},
              callback: function(record){
                  deleteRecordRow(record);
                  $("#pnlRecordDelete .btn-close").click();
            }
          },
          webRequest);

      $("#pnlRecordDelete").modal({
          escapeClose: false,
          clickClose: false,
          showClose: false
      });

}


function openRecordAddEditSecretPanel(e){
    e.preventDefault();

    let pnl = $("#pnlRecordSecretAddEdit");
    let tr = $(e.target).parent().parent();

    //clear data
    pnl.find("#lblAction").html("");
    pnl.find("#txtRecordSecretKey").val("");
    pnl.find("#txtRecordSecretValue").val("");
    pnl.find("#hdnRecordId").val(0);

    //Set data
    let method = "";
    let callback = null;
    pnl.find("#hdnRecordId").val(tr.attr("repo-id"));

    if(e.data.mode == "ADD"){
        pnl.find("#lblAction").html("Add Secret");
        pnl.find("#txtRecordSecretKey").prop( "disabled", false );
        method = "POST";
        callback = function(secret){
            addSecretRow(secret);
            $("#pnlRecordSecretAddEdit .btn-close").click();
        }
    }else if(e.data.mode == "EDIT"){
        pnl.find("#lblAction").html("Edit Secret");
        pnl.find("#txtRecordSecretKey").prop( "disabled", true );
        method = "PUT";
        callback = function(secret){
             $("#pnlRecordSecretAddEdit .btn-close").click();
         }
    }

    pnl.find("#txtRecordSecretKey").val(tr.attr("secret-key"));
    pnl.find("#txtRecordSecretValue").val("");

    pnl.find(".btn-save").off('click');
    pnl.find(".btn-save").on(
        'click', {
            url: "http://" + window.location.host + "/api/secrets",
            method: method,
            getData: function(){
                let pnl = $("#pnlRecordSecretAddEdit");
                let d = {
                    recordId: pnl.find("#hdnRecordId").val(),
                    key: pnl.find("#txtRecordSecretKey").val(),
                    value: pnl.find("#txtRecordSecretValue").val()
                };
                return d;
            },
            callback: callback
        },
        webRequest);

    $("#pnlRecordSecretAddEdit").modal({
        escapeClose: false,
        clickClose: false,
        showClose: false
    });
}

function openRecordSecretDeletePanel(e){
    e.preventDefault();
    let pnl = $("#pnlRecordSecretDelete");
    let tr = $(e.target).parent().parent();

    pnl.find("#hdnRecordId").val(tr.attr("repo-id"));
    pnl.find("#hdnRecordSecretKey").val(tr.attr("secret-key"));

    pnl.find(".btn-ok").off('click');
    pnl.find(".btn-ok").on(
        'click', {
            url: "http://" + window.location.host + "/api/secrets",
            method: "DELETE",
            getData: function(){
                let pnl = $("#pnlRecordSecretDelete");
                let d = {
                    recordId: pnl.find("#hdnRecordId").val(),
                    key: pnl.find("#hdnRecordSecretKey").val()
                };
                return d;
        },
        callback: function(secret){
            deleteRecordSecretRow(secret);
            $("#pnlRecordSecretDelete .btn-close").click();
        }
        },
        webRequest);

    $("#pnlRecordSecretDelete").modal({
        escapeClose: false,
        clickClose: false,
        showClose: false
    });
}

function openRecordSecretCheckPanel(e){
    e.preventDefault();
    let pnl = $("#pnlRecordSecretCheck");
    let tr = $(e.target).parent().parent();

    pnl.find("#txtRecordSecretValue").val("");

    pnl.find("#hdnRecordId").val(tr.attr("repo-id"));
    pnl.find("#hdnRecordSecretKey").val(tr.attr("secret-key"));

    pnl.find(".btn-ok").off('click');
    pnl.find(".btn-ok").on(
        'click', {
            url: "http://" + window.location.host + "/api/secrets/check",
            method: "POST",
            getData: function(){
                let pnl = $("#pnlRecordSecretCheck");
                let d = {
                    recordId: pnl.find("#hdnRecordId").val(),
                    key: pnl.find("#hdnRecordSecretKey").val(),
                    value: pnl.find("#txtRecordSecretValue").val(),
                };
                return d;
            },
            callback: function(check){
                secret={
                    recordId: pnl.find("#hdnRecordId").val(),
                    key: pnl.find("#hdnRecordSecretKey").val()
                };
                notifyRecordSecretCheck(secret, check);
                $("#pnlRecordSecretCheck .btn-close").click();
            }
        },
        webRequest);

    $("#pnlRecordSecretCheck").modal({
        escapeClose: false,
        clickClose: false,
        showClose: false
    });
}

function openErrorPanel(error){
    $("#pnlError").modal();
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
