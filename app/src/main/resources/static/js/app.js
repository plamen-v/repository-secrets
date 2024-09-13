$( document ).ready(function() {


    $.ajax({
        url: "http://localhost:8081/api/records",
        type: 'GET',
       // headers: { 'Access-Control-Allow-Origin': '*' },
        success: function(res) {
           console.log(res);
           addRecords(res);
        }
    });
});


function addRecords(records){
    console.log("in");
     let tBody = $('#tblRecords > tbody');
     let rows = "";

     $.each(records, function( index, record) {
        rows += generateRecordRow(record);
        $.each(record.secrets, function(j, secret){
            rows += generateSecretRow(secret);
        });
     });


     tBody.html(rows);
}

function generateRecordRow(record){
    return "<tr><td>" + record.url + "</td><td><a>add secret</a> <a>edit</a> <a>elete</a></td></tr>";
}

function generateSecretRow(secret){
    return "<tr><td>" + secret + "</td><td><a>add secret</a> <a>edit</a> <a>elete</a></td></tr>";
}
