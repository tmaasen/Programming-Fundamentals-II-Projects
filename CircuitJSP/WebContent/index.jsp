<%@ page import="model.Circuit"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta charset="ISO-8859-1">
<title>Ohm's Law</title>
</head>
<body>
<!-- Programmer -> tomcat -> html -> Chrome -> end user -->
Today is <%= new java.util.Date() %>.
<br><br>
Welcome to the Ohm's Law calculator! This webpage is designed to help you calculate Ohm's Law
anyway you would like.
<br><br>
First of all, please choose what you would like to calculate: Voltage, Amperage, or Resistance?
<br><br>
<hr>

<form>
		<div id="directions">
			<h4>Directions</h4>
			<p>If calculating Voltage, please only enter values in the Amperage and Resistance fields.</p>
			<p>If calculating Amperage, please only enter values in the Voltage and Resistance fields.</p>
			<p>If calculating Resistance, please only enter values in the Voltage and Amperage fields.</p>
		</div>
	<h3>Calculate:</h3>
	<input type="radio" name="ohmsLaw" value="Voltage" checked>
		<label>Voltage</label>
	<input type="radio" name="ohmsLaw" value="Amperage">
		<label>Amperage</label>
	<input type="radio" name="ohmsLaw" value="Resistance">
		<label>Resistance</label>
<br><br>
<!-- Could use required input type as well -->
	<input type="number" step="0.01" placeholder="[Enter Voltage]" name="txtVoltage">
<br><br>
	<input type="number" step="0.01" placeholder="[Enter Amperage]" name="txtAmperage">
<br><br>
	<input type="number" step="0.01" placeholder="[Enter Resistance]" name="txtResistance">
<br><br>
	<input type="submit" name="txtSubmit" value="Calculate">
	<input type="submit" name="txtReset" value="Reset Form">

	<%
	Circuit myCircuit = new Circuit();
	
	if (request.getParameter("txtSubmit") != null) {
		if (request.getParameter("ohmsLaw").equals("Voltage")) {
			myCircuit.setAmperage(Double.parseDouble(request.getParameter("txtAmperage")));
			myCircuit.setResistance(Double.parseDouble(request.getParameter("txtResistance")));
			myCircuit.calcualteVoltage();
			out.println("<br><br>The voltage is " + myCircuit.getVoltage()); 
		} else if (request.getParameter("ohmsLaw").equals("Amperage")) {
			myCircuit.setVoltage(Double.parseDouble(request.getParameter("txtVoltage")));
			myCircuit.setResistance(Double.parseDouble(request.getParameter("txtResistance")));
			myCircuit.calcualteAmperage();
			out.println("<br><br>The amperage is " + myCircuit.getAmperage());
		} else {
			myCircuit.setVoltage(Double.parseDouble(request.getParameter("txtVoltage")));
			myCircuit.setAmperage(Double.parseDouble(request.getParameter("txtAmperage")));
			myCircuit.calcualteResistance();
			out.println("<br><br>The resistance is " + myCircuit.getResistance());
			}
		}
	%>
</form>
<br>
<hr>
</body>
</html>