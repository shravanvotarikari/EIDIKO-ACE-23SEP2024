<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" indent="no"/>

    <xsl:template match="/">
        {
            "ids": {
                <xsl:for-each select="/employees/employee">
                     "<xsl:value-of select="@id"/>"
                    <xsl:if test="position() != last()">, </xsl:if>
                </xsl:for-each>
            },
            "names": {
                <xsl:for-each select="/employees/employee">
                    "<xsl:value-of select="name"/>"
                    <xsl:if test="position() != last()">, </xsl:if>
                </xsl:for-each>
            },
            "positions": {
                <xsl:for-each select="/employees/employee">
                   "<xsl:value-of select="position"/>"
                    <xsl:if test="position() != last()">, </xsl:if>
                </xsl:for-each>
            },
            "salaries": {
                <xsl:for-each select="/employees/employee">
                     "<xsl:value-of select="salary"/>"
                    <xsl:if test="position() != last()">, </xsl:if>
                </xsl:for-each>
            }
        }
    </xsl:template>
</xsl:stylesheet>
