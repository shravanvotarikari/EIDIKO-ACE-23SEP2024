<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:json="http://www.ibm.com/xmlns/prod/2009/json" exclude-result-prefixes="json">

    <xsl:output method="text" indent="no"/>

    <xsl:template match="/">
        {
            "employee1": {
                "id": "<xsl:value-of select='/employees/employee[1]/@id'/>",
                "name": "<xsl:value-of select='/employees/employee[1]/name'/>",
                "position": "<xsl:value-of select='/employees/employee[1]/position'/>",
                "salary": <xsl:value-of select='/employees/employee[1]/salary'/>
            },
            "employee2": {
                "id": "<xsl:value-of select='/employees/employee[2]/@id'/>",
                "name": "<xsl:value-of select='/employees/employee[2]/name'/>",
                "position": "<xsl:value-of select='/employees/employee[2]/position'/>",
                "salary": <xsl:value-of select='/employees/employee[2]/salary'/>
            },
            "employee3": {
                "id": "<xsl:value-of select='/employees/employee[3]/@id'/>",
                "name": "<xsl:value-of select='/employees/employee[3]/name'/>",
                "position": "<xsl:value-of select='/employees/employee[3]/position'/>",
                "salary": <xsl:value-of select='/employees/employee[3]/salary'/>
            }
        }
    </xsl:template>

</xsl:stylesheet>
