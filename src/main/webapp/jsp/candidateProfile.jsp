<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>UI</title>
        <meta name="description" content="An interactive getting started guide for Brackets.">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
        <script type="text/javascript" src = "../resources/core/js/jquery-3.2.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script src="https://d3js.org/d3.v4.min.js"></script>
        <script type="text/javascript" src = "../resources/core/js/parseNameFromURL.js"></script>
        <style>
        #top{
            height: 150px;
            background-color: #003657;
            background-repeat: no-repeat;
            background-size: 100% 250px;
        }

        #medium{
            height:400px;
        }

        #bottom{
            height: 600px;
            background-color: lightgrey;
        }
        * {
            box-sizing: border-box;
        }
        #myInput {
          background-position: 10px 12px;
          background-repeat: no-repeat;
          width: 100%;
          font-size: 16px;
          padding: 12px 20px 12px 40px;
          border: 1px solid #ddd;
          margin-bottom: 12px;
        }

        #navbar{
            position: absolute;
            top:190px;
        }
        .borders{
            border: 5px solid white;
        }
        .box{
            width: 200px;
            height: 400px;
            background: white;
        }
        nav ol{
            height: 300px;
            width:100%;
        }
        #listContainer {
          list-style-type: circle;
          padding: 0;
          margin: 0;
        }

        #listContainer li a {
          border: 1px solid #ddd;
          margin-top: -1px; /* Prevent double borders */
          background-color: #f6f6f6;
          padding: 12px;
          text-decoration: none;
          font-size: 18px;
          color: black;
          display: block
        }


        #listContainer li a:focus:not(.header) {
              background-color: #0085D6;
        }

        nav ol{
            overflow: hidden;
            overflow-y: scroll;
        }
        .coverphoto{
            height: 150px;
            background-color: lightblue;
            margin-left: 1px;
            margin-top: 1px;
            background-repeat: no-repeat;
            background-size: 100% 150px;
        }
        .profilePic{

        }
        #logo{
            display: block;
            max-width:250px;
            max-height:250px;
            width: auto;
            height: auto;
        }
        #name{
            font-family: sans-serif;
            font-size: 22px;
        }

        #From{
            font-family: sans-serif;
            font-size: 18px;
            margin-top: -22px;

        }

        .back{
            height:400px;
            background-color: #2f749d;
            background-repeat: no-repeat;
            background-size: 100% 400px;
        }
        .img-circle{
            border-radius: 50%;
            background-repeat: no-repeat;
            background-position: 50%;
            border-radius: 50%;
            margin-top: -50px;
            position: relative;
            width: 100px;
            height: 100px;
        }



    </style>
</head>
<script type="text/javascript">

</script>

<!--
<script type="text/javascript">
var reader = new XMLHttpRequest() || new ActiveXObject('MSXML2.XMLHTTP');

function loadFile() {
    reader.open('get', '../resources/core/images/hello.txt', true);
    reader.onreadystatechange = displayContents;
    reader.send(null);
}

function displayContents() {
    if(reader.readyState==4) {
        var el = document.getElementById('main');
        el.innerHTML = reader.responseText;
    }
}


    </script>
-->
<script>
function myFunction() {
    var input, filter, ul, li, a, i;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    ul = document.getElementById("listContainer");
    li = ul.getElementsByTagName("li");
    for (i = 0; i < li.length; i++) {
        a = li[i].getElementsByTagName("a")[0];
        if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";

        }
    }
}
</script>
<script>
function myFunction1()
{
    document.getElementById("listContainer").style.backgroundColor = "red";
}
</script>
    <body  >

		<div id="top" class="img-responsive">
            <div><img id="logo" style="padding: 40px 0px 0px 30px" src="../resources/core/images/logos.png">
            </div>

        </div>
        <div id="medium">
            <div class="row ">
                <div class="col-md-4 box borders back" >
                    <div>
                        <nav class="navbar navbar-light justify-content-between" style="background-color: lightblue;">
                            <a class="navbar-brand" style="color: white">Rank</a>
                            <form class="form-inline">
                            <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for names.." title="Type in a name">

                            </form>
                        </nav>
                    </div>
                    <div>
                    <nav>
                        <ol id="listContainer">

                        </ol>
                    </nav>
                    </div>
                </div>
                <div class="col-md-4 box borders back "align="center">
                   <div class="coverphoto"></div>
                    <div class="profilePic">
                    <img class = "img-circle" src = "../resources/core/images/unknown-sq.jpg">
                    </div>
                    <div>
                        <p id="name"> Bill Gates</p>
                        <p id="From">Pune,Maharashtra</p>
                        <hr>
                    </div>
                    <p style="margin-left: 20px;color: black; text-align: center"> Total Score</p>
                    <p id = "score"> 100</p>

                </div>
                <div class="col-md-4 box borders back" >
                    <div id="pieChart" style="margin-left:-10px;margin-right:-10px;background-image: url(backgroundForSecond.jpg);"></div>
                </div>
            </div>

        </div>
        <div id="bottom">
            <div class="row">

                <div class="col-md-12">
                <iframe id = "resume" src="" style="width: 100%;height:600px;border: 5px solid white ; " />
                                      </div>
            </div>
        </div>
        <div id="div1"></div>
    </body>
</html>