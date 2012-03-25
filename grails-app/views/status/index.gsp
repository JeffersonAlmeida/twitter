<html>
<head>
    <meta name="layout" content="main"/>
    <title>What Are You Doing?</title>
    <g:javascript library="jquery" plugin="jquery" />
</head>
<body>
  
     <h1>Search For People To Follow</h1>
    <div class="searchForm">
        <g:form controller="searchable">
            <g:textField name="q" value=""/>
        </g:form>
    </div> 

   
    <h1>What are you doing <sec:username/> ?<br/><br/></h1>
    <div class="updatStatusForm">
        <g:formRemote url="[action: 'updateStatus']" update="messages" name="updateStatusForm"
                      onSuccess="document.updateStatusForm.message.value='';">
            <g:textArea name="message" value=""/><br/>
            <g:submitButton name="Update Status"/>
        </g:formRemote>
    </div>
    
    <div id="messages"
          <g:render template="messages" collection="${statusMessages}" var="statusMessage"/>
     </div>
      
</body>
</html>