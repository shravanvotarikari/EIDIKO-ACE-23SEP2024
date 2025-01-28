<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
 <xsl:output method="text" />

    <xsl:template match="/">
        {
        <xsl:apply-templates select="*" />
        }
    </xsl:template>

    <xsl:template match="*">
        "<xsl:value-of select="name()" />": 
        <xsl:choose>
            <xsl:when test="*">
                {
                <xsl:apply-templates select="*" />
                }
            </xsl:when>
            <xsl:otherwise>
                "<xsl:value-of select="." />"
            </xsl:otherwise>
        </xsl:choose>
        <xsl:if test="following-sibling::*">, </xsl:if>
    </xsl:template>
</xsl:stylesheet>
