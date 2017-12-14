<html>
<head>
    <title>Search</title>
            <script type="text/javascript" src = "../resources/core/js/jquery-3.2.1.min.js"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link rel="stylesheet/less" type="text/css" href="../resources/core/less/style.less">
    <!--<link rel="stylesheet" href="../resources/core/css/slideshow.css">-->
    <script src="//cdnjs.cloudflare.com/ajax/libs/less.js/2.7.2/less.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
    <script src="../resources/core/js/script.js"></script>
    <script crossorigin src = "https://unpkg.com/tooltip.js"></script>

</head>

<body style = "background: url('../resources/core/images/gradient.jpg') no-repeat center;">
<div id="bigdiv" >
    <!--<ul class="back-slideshow">
        <li>
            <span>Back01</span>
            <!-- <div>
            <h3>City 1</h3>
            </div> -->
        </li>
       <!-- <li>
            <!--<span>Back02</span>-->
            <!-- <div>
            <h3>City 2</h3>
            </div> -->
        </li>
        <!--<li>
           <!-- <span>Back03</span>-->
            <!-- <div>
            <h3>City 3</h3>
            </div> -->
        </li>
    </ul>
    <nav align="left" class="navbar navbar-fixed-top">
        <div class="container">
                <div class="navbar-header" style="width:144px"><img src="../resources/core/images/ideas.png" style="max-width:100%; max-height:100%; padding: 10px" /></div>

        </div>
    </nav>
    <div align="center" id="centerCard" class="container">
        <div id="heading"></div><br />

        <form class="form-inline" action="/something" name="candidateSearch" method="get">
            <select id = "dropdown" name = "dropdown">
                   <option value="SD" class="dept"> Software Developer</option>
                   <option value="QA" class="dept"> Quality Assuarance</option>
            </select>
            <div class="form-group" style="border-bottom: 1px solid #ababab; position:relative; left:21%;margin-top: -20px;">
                <div>
                    <input id="catagory" name="catagory"  placeholder="Enter Requirements..."  type="text" required>
                </div>
            </div>
            <br><br><br>


            <div class="form-group" style="border-bottom: 1px solid #ababab; position:relative; left:21%;margin-top: -70px;">
                  <input id="experience" name="experience" placeholder="Enter experience Requirements..."  type="text" required>
            </div>
            <input class = "btn btn-secondary" type='submit' style = "margin-top: 200px;margin-left: -180px;"></input>
        </form>
    </div>


</div>
</body>
</html>
