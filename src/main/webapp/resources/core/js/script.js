function onSubmit(){

        var requirements = $("#catagory").val();
        var experience  = $("experience").val();
        $.get("/saveToDataBase?catagory="+requirements+"&experience=" +experience);

     }
