<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:json="http://www.ibm.com/xmlns/prod/2009/json" exclude-result-prefixes="json">

    <xsl:output method="text" indent="no"/>

    <xsl:template match="/">
        {
            <xsl:for-each select="/employees/employee">
                <xsl:if test="position() > 1">,</xsl:if>
                "employee": {
                    "id": "<xsl:value-of select='@id'/>",
                    "name": "<xsl:value-of select='name'/>",
                    "position": "<xsl:value-of select='position'/>",
                    "salary": <xsl:value-of select='salary'/>
                }
            </xsl:for-each>
        }
    </xsl:template>

</xsl:stylesheet>
