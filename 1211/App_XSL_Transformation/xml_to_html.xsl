<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <xsl:output method="html" indent="yes" />

    <!-- Root template -->
    <xsl:template match="/">
        <html>
            <head>
                <title>Employee Details</title>
                <style>
                    table {
                        border-collapse: collapse;
                        width: 100%;
                    }
                    th, td {
                        border: 1px solid black;
                        padding: 8px;
                        text-align: left;
                    }
                    th {
                        background-color: #f2f2f2;
                    }
                </style>
            </head>
            <body>
                <h1>Employee List</h1>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Department</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:apply-templates select="Company/Employee" />
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>

    <!-- Template for Employee elements -->
    <xsl:template match="Employee">
        <tr>
            <td><xsl:value-of select="ID" /></td>
            <td><xsl:value-of select="Name" /></td>
            <td><xsl:value-of select="Department" /></td>
        </tr>
    </xsl:template>
</xsl:stylesheet>
