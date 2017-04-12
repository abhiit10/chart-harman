<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Harman chart details</title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 52em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="status" role="complementary">
			<h1>
				Please Upload ur Excel file
			</h1>

			<g:form controller="upload" action="processData" enctype="multipart/form-data">

				<input type="radio" name="filter" value="Status." checked> Allocation report.<br>
				%{--<input type="radio" name="filter" value="Employment Type"> Employment Type<br>
				<input type="radio" name="filter" value="Grade [stHRsuite]"> Grade [stHRsuite]<br>
				<input type="radio" name="filter" value="Country"> Country<br>
				<input type="radio" name="filter" value="Onsite/Offshore"> Onsite/Offshore<br>
				<input type="radio" name="filter" value="Location [stHRsuite]"> Location [stHRsuite] <br>
				<input type="radio" name="filter" value="Division (Including Horizontals)" > Division (Including Horizontals)<br>
				<input type="radio" name="filter" value="SBU [stHRsuite]"> SBU [stHRsuite]<br>
				<input type="radio" name="filter" value="Final BU"> Final BU <br>
				<input type="radio" name="filter" value="BU [stHRSuite]"> BU [stHRSuite] <br>
				<input type="radio" name="filter" value="Home GOC [stHRsuite]"> Home GOC [stHRsuite] <br>
				<input type="radio" name="filter" value="Reporting Manager Name"> Reporting Manager Name<br>
				<input type="radio" name="filter" value="Home GOC Manager"> Home GOC Manager<br>--}%



				<div style="margin-top: 3em;">
					<input type="file" name="doc" >
					<input type="submit" value="Upload">
				</div>
			</g:form>

		</div>
	</body>
</html>
