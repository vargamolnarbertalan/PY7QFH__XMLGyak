<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
	<html>
		<body>
			<h2>Vizsgák for-each</h2>
				
			<table border = "3">
				<tr bgcolor ="green">
					<th>kurzus</th>
					<th>helyszin</th>
					<th>idopont</th>
					<th>oktato</th>
					<th>jegy</th>
				</tr>
				
				<xsl:for-each select="root/vizsgak/vizsga">
				<tr>
					<td><xsl:value-of select = "kurzus"/></td>
					<td><xsl:value-of select = "helyszin"/></td>
					<td><xsl:value-of select = "idopont"/></td>
					<td><xsl:value-of select = "oktato"/></td>
					<td><xsl:value-of select = "jegy"/></td>					
				</tr>
				</xsl:for-each>                                
			</table>
            <h2>Jegyek atlaga: <xsl:value-of select="sum(/root/vizsgak/vizsga/jegy) div count(/root/vizsgak/vizsga/jegy)" /></h2>
		</body>
	</html>
	</xsl:template>
</xsl:stylesheet>