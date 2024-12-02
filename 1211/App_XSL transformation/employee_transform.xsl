<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <!-- Root template -->
    <xsl:template match="/">
        <EmployeeDetails>
            <!-- Iterate over each Employee -->
            <xsl:for-each select="Employees/Employee">
                <Employee>
                    <EmpID>
                        <xsl:value-of select="ID"/>
                    </EmpID>
                    <FullName>
                        <xsl:value-of select="Name"/>
                    </FullName>
                    <Dept>
                        <xsl:value-of select="Department"/>
                    </Dept>
                </Employee>
            </xsl:for-each>
        </EmployeeDetails>
    </xsl:template>
</xsl:stylesheet>
