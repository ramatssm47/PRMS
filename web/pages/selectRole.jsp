<%-- 
    Document   : dialog
    Created on : Sep 10, 2014, 9:19:32 PM
    Author     : gautamverma
--%>

<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>jQuery UI Dialog - Modal form</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <style>
    body { font-size: 62.5%; }
    label, input { display:block; }
    input.text { margin-bottom:12px; width:95%; padding: .4em; }
    fieldset { padding:0; border:0; margin-top:25px; }
    h1 { font-size: 1.2em; margin: .6em 0; }
    div#users-contain { width: 350px; margin: 20px 0; }
    div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
    div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
    .ui-dialog .ui-state-error { padding: .3em; }
    .validateTips { border: 1px solid transparent; padding: 0.3em; }
  </style>
  <script>
  $(function() {
      
   
      
    var dialog, form,
 
  
 
  
//    dialog = $( "#dialog-form" ).dialog({
//      autoOpen: false,
//      height: 300,
//      width: 350,
//      modal: true,
//      buttons: {
//        OK: function() {
//          dialog.dialog( "close" );
//        },
//        Cancel: function() {
//          dialog.dialog( "close" );
//        }
//      },
//      close: function() {
//        form[ 0 ].reset();
//        allFields.removeClass( "ui-state-error" );
//      }
//    });
 
    
//   dialog.dialog( "open" );
    
    
    
    
  });
  </script>
</head>
<body>
 
<div id="dialog-form" title="Create new user">
  <p class="validateTips">All form fields are required.</p>
 
  <form>
      
    <fieldset>
      
    </fieldset>
  </form>
</div>
 
 

<div id="somediv"></div>
</body>
</html>