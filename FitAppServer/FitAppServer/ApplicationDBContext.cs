using Microsoft.EntityFrameworkCore;
using FitAppServer.Models;

namespace FitAppServer
{
    public class ApplicationDBContext : DbContext
    {
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            var builder = new ConfigurationBuilder()
                .SetBasePath(Directory.GetCurrentDirectory())
                .AddJsonFile("appsettings.json", optional: true, reloadOnChange: true);
            IConfigurationRoot configuration = builder.Build();
            optionsBuilder.UseSqlServer(configuration.GetConnectionString("DbCnn"));
        }

        public virtual DbSet<Assign> Assigns { get; set; }
        public virtual DbSet<Exercise> Exercises { get; set; }
        public virtual DbSet<ExerciseStep> ExerciseSteps { get; set; }
        public virtual DbSet<Schedule> Schedules { get; set; }
        public virtual DbSet<User> Users { get; set; }
        public virtual DbSet<UserRole> UserRoles { get; set; }
        public virtual DbSet<FCMToken> FCMTokens { get; set; }


        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            // Map N-N
            modelBuilder.Entity<Exercise>()
            .HasMany(e => e.Schedules)
            .WithMany(e => e.Exercises)
            .UsingEntity<ExerciseSchedule>(
                j => j.HasOne(mfs => mfs.Schedule).WithMany(),
                j => j.HasOne(mfs => mfs.Exercise).WithMany()
            );


        }
    }
}
