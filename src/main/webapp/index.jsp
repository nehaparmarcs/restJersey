<style type="text/css">
body {background-color:#d4e3e5;}
.tftable th {border-width: 0px;padding: 8px;}
.tftable tr {border-width: 0px;padding: 8px;}
.tftable td {border-width: 0px;padding: 8px;}
</style>
<script>
function handleClick(myRadio) {
    //alert('New value: ' + myRadio.value);
    alert(myRadio.value);
    alert(myRadio.value == rb1);
 
}
</script>

<body>

<table align="center" class="tftable" border="1" width= "300" height="400">
<tr><th colspan="2" align = "center">Device Management and information reporting</th></tr>
<tr><td></td></tr>
<tr><td>Choose operation from below: <br>
<input type="radio" id="rb1" name="rb" value="rb1" onclick="handleClick(this)">
<label for="rb1">Create Client Object</label><br>
<input type="radio" id="rb2" name="rb"value="rb2" onclick="handleClick(this)">
<label for="rb2">Write to Client</label><br>
<input type="radio" id="rb3" name="rb" value="rb3" onclick="handleClick(this)">
<label for="rb3">Read From Client</label><br>
<input type="radio" id="rb4" name="rb" value="rb4"  onclick="handleClick(this)">
<label for="rb4">Write Attribute to Client</label><br>
<input type="radio" id="rb5" name="rb"value="rb5" onclick="handleClick(this)">
<label for="rb5">Discover Client object</label><br>
<input type="radio" id="rb6" name="rb" value="rb6" onclick="handleClick(this)">
<label for="rb6">Delete Client</label>

</td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></tr>

</table>

</body>