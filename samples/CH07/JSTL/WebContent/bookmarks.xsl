<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
     xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
      <html>
          <head>
              <meta charset="UTF-8"/>
          </head>
          <body>
            <h2>線上書籤</h2>
            <table border="1">
                <tr bgcolor="#00ff00">
                    <th align="left">名稱</th>
                    <th align="left">網址</th>
                    <th align="left">分類</th>
                </tr>
                <xsl:for-each select="bookmarks/bookmark">
                <tr>
                    <td><xsl:value-of select="title"/></td>
                    <td><xsl:value-of select="url"/></td>
                    <td><xsl:value-of select="category"/></td>
                </tr>
                </xsl:for-each>
            </table>
        </body>
      </html>
    </xsl:template>
</xsl:stylesheet>
