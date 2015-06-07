
using System.Data.Entity;
using Kappa.Data.Models;

namespace Kappa.Data
{
    public class KappaContext : DbContext
    {
        public KappaContext()
            :base("Kappa")
        {
        }

        public DbSet<Request> Requests { get; set; }

        public DbSet<Institution> Institutions { get; set; }

        public DbSet<Situation> Situations { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Request>().
              HasMany<Institution>(c => c.Institutions).
              WithMany(p => p.Requests).
              Map(
               m =>
               {
                   m.MapLeftKey("RequestId");
                   m.MapRightKey("InstitutionId");
                   m.ToTable("RequestsInstitutions");
               });

            modelBuilder.Entity<Request>().
              HasMany<Situation>(c => c.Situations).
              WithMany(p => p.Requests).
              Map(
               m =>
               {
                   m.MapLeftKey("RequestId");
                   m.MapRightKey("SituationId");
                   m.ToTable("RequestsSituations");
               });
        }
    }
}
