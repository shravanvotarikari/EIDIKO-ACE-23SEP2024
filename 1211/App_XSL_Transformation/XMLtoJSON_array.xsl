<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <xsl:output method="text" />

    <xsl:template match="/">
        {
        <xsl:apply-templates select="*" />
        }
    </xsl:template>

    <!-- Template for elements -->
    <xsl:template match="*">
        <xsl:if test="not(preceding-sibling::*[name() = name(current())])">
            "<xsl:value-of select="name()" />": 
            <xsl:choose>
                <!-- If the element has multiple siblings with the same name, treat as an array -->
                <xsl:when test="count(../*[name() = name(current())]) > 1">
                    [
                    <xsl:for-each select="../*[name() = name(current())]">
                        {
                        <xsl:apply-templates select="*" />
                        }
                        <xsl:if test="position() != last()">, </xsl:if>
                    </xsl:for-each>
                    ]
                </xsl:when>
                <!-- If the element has child elements -->
                <xsl:when test="*">
                    {
                    <xsl:apply-templates select="*" />
                    }
                </xsl:when>
                <!-- If the element has text only -->
                <xsl:otherwise>
                    "<xsl:value-of select="." />"
                </xsl:otherwise>
            </xsl:choose>
            <xsl:if test="following-sibling::*[not(name() = name(current()))]">, </xsl:if>
        </xsl:if>
    </xsl:template>
</xsl:stylesheet>
