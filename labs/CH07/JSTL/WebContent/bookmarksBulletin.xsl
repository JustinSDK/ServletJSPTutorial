<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
      xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="headline"/>
    <xsl:template match="/">
        <h2><xsl:value-of select="$headline"/></h2>
        <ul>
            <xsl:for-each select="bookmarks/bookmark">
            <li><xsl:value-of select="title"/></li>
            <ul>
                <li><xsl:value-of select="url"/></li>
                <li><xsl:value-of select="category"/></li>
            </ul>             
            </xsl:for-each>
        </ul>
    </xsl:template>
</xsl:stylesheet>
