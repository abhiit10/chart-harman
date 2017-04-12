<html>
<head>
    <meta name="layout" content="main"/>

    <script type="text/javascript" src="/ChartHarman/assets/d3.v4.js"></script>
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/d3/3.4.4/d3.min.js"></script>
    <script type="text/javascript" src="/ChartHarman/assets/d3pie.js"></script>
    <script type="text/javascript" src="/ChartHarman/assets/d3pie.min.js"></script>


    <style>

    .button {
        font: bold 11px Arial;
        text-decoration: none;
        background-color: #EEEEEE;
        color: #333333;
        padding: 8px 39px;
        border-top: 1px solid #CCCCCC;
        border-right: 1px solid #333333;
        border-bottom: 1px solid #333333;
        border-left: 1px solid #CCCCCC;
        margin-left: 2.3em;
    }
    </style>

</head>
<body>

<span style="text-align: center"> <h1>This Graph has been generated for filter - ${filerData}</h1></span>

<g:form action="advanceFilter">
    Employment Type : <g:select from="${empFilter}" name="emp" noSelection="['':'Please Select']" value="${params.emp}"></g:select> <br>
    Grade [stHRsuite] : <g:select from="${gradeFilter}" name="grade" noSelection="['':'Please Select']" value="${params.grade}"></g:select>  <br>
    Onsite/Offshore : <g:select from="${onSiteFilter}" name="onsite" noSelection="['':'Please Select']" value="${params.onsite}"></g:select>  <br>
    Division (Including Horizontals) : <g:select from="${divisionFilter}" name="division" noSelection="['':'Please Select']" value="${params.division}"></g:select> <br>
    SBU [stHRsuite] : <g:select from="${sbuFilter}" name="sbu" noSelection="['':'Please Select']" value="${params.sbu}"></g:select> <br>
    Home GOC [stHRsuite] : <g:select from="${homeFilter}" name="home" noSelection="['':'Please Select']" value="${params.home}" ></g:select> <br>
    Reporting Manager Name : <g:select from="${rManagerFilter}" name="rManager" noSelection="['':'Please Select']" value="${params.rManager}"></g:select> <br>
    Final BU : <g:select from="${finalFilter}" name="final" noSelection="['':'Please Select']" value="${params.final}"></g:select> <br>
    Home GOC Manager : <g:select from="${homeGocFilter}" name="homeGoc" noSelection="['':'Please Select']" value="${params.homeGoc}"></g:select> <br>

    <input type="submit" value="Advanced Filter">
</g:form>

<div id="pieChart"></div>

<a href="../" class="button">Back</a>


<script type="text/javascript">

    //var content = '${content}';
    var arrayOfObjects = ${raw(content)};

    var pie = new d3pie("pieChart", {
        "header": {
            "title": {
                "text": "Harman Official data",
                "fontSize": 24,
                "font": "open sans"
            },
            "subtitle": {
                "text": "A full pie chart to show off label collision detection and resolution.",
                "color": "#999999",
                "fontSize": 12,
                "font": "open sans"
            },
            "titleSubtitlePadding": 9
        },
        "footer": {
            "color": "#999999",
            "fontSize": 10,
            "font": "open sans",
            "location": "bottom-left"
        },
        "size": {
            "canvasWidth": 700,
            "pieOuterRadius": "90%"
        },
        "data": {
            "sortOrder": "value-desc",
            "content": ${raw(content)}
        },
        "labels": {
            "outer": {
                "pieDistance": 32
            },
            "inner": {
                "hideWhenLessThanPercentage": 3
            },
            "mainLabel": {
                "fontSize": 11
            },
            "percentage": {
                "color": "#ffffff",
                "decimalPlaces": 0
            },
            "value": {
                "color": "#adadad",
                "fontSize": 11
            },
            "lines": {
                "enabled": true
            },
            "truncation": {
                "enabled": true
            }
        },
        "effects": {
            "pullOutSegmentOnClick": {
                "effect": "back",
                "speed": 400,
                "size": 8
            }
        },
        "tooltips": {
            "enabled": true,
            "type": "placeholder",
            "string": "{label}: {value}, {percentage}%"
        },
        "misc": {
            "colors": {
                "background": "#ffffff"
            },
            "gradient": {
                "enabled": true,
                "percentage": 100
            }
        }
    });
</script>





</body>
</html>