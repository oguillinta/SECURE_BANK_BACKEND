using Microsoft.EntityFrameworkCore;

namespace ApiSecureBank.DBContext
{
    public class ApplicationDBContext : DbContext
    {
        public ApplicationDBContext(DbContextOptions<ApplicationDBContext> options) : base(options)
        {
        }
        public DbSet<Entities.InterestRate> InterestRates { get; set; } 
        public DbSet<Entities.Account> Accounts { get; set; }
        public DbSet<Entities.Customer> Customers { get; set; }
        public DbSet<Entities.AccountSummaryReport> AccountSummaryReports { get; set; }
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Entities.InterestRate>().ToTable("InterestRates");
            modelBuilder.Entity<Entities.Account>().ToTable("Accounts");
            modelBuilder.Entity<Entities.Customer>().ToTable("Customers");
            modelBuilder.Entity<Entities.AccountSummaryReport>().ToTable("AccountSummaryReports");
            base.OnModelCreating(modelBuilder);
        }
    }
}
