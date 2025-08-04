using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace ApiSecureBank.Migrations
{
    /// <inheritdoc />
    public partial class EntityAccountSummaryReport : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "AccountSummaryReports",
                columns: table => new
                {
                    Id = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    accountType = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    totalAccounts = table.Column<int>(type: "int", nullable: false),
                    totalBalance = table.Column<decimal>(type: "decimal(18,2)", nullable: false),
                    averageBalance = table.Column<decimal>(type: "decimal(18,2)", nullable: false),
                    activeAccountsCount = table.Column<int>(type: "int", nullable: false),
                    frozenAccountsCount = table.Column<int>(type: "int", nullable: false),
                    closedAccountsCount = table.Column<int>(type: "int", nullable: false),
                    reportGeneratedAt = table.Column<DateTime>(type: "datetime2", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_AccountSummaryReports", x => x.Id);
                });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "AccountSummaryReports");
        }
    }
}
