﻿using Microsoft.EntityFrameworkCore;
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
            //optionsBuilder.UseMySQL(configuration.GetConnectionString("DbCnn"));
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

            modelBuilder.Entity<Assign>()
            .HasOne(a => a.Trainee)
            .WithOne(u => u.TraineeAssignment)
            .HasForeignKey<Assign>(a => a.TraineeId)
            .OnDelete(DeleteBehavior.Cascade);

            modelBuilder.Entity<Assign>()
                .HasOne(a => a.Trainer)
                .WithMany(u => u.TrainerAssignments)
                .HasForeignKey(a => a.TrainerId)
                .OnDelete(DeleteBehavior.Restrict);

            modelBuilder.Entity<Schedule>()
            .HasOne(a => a.Trainee)
            .WithMany(u => u.TraineeSchedules)
            .HasForeignKey(a => a.TraineeId)
            .OnDelete(DeleteBehavior.Cascade);

            modelBuilder.Entity<Schedule>()
                .HasOne(a => a.Trainer)
                .WithMany(u => u.TrainerSchedules)
                .HasForeignKey(a => a.TrainerId)
                .OnDelete(DeleteBehavior.Restrict);

            modelBuilder.Entity<Assign>().HasKey(t => new { t.TrainerId, t.TraineeId });

            // Create user roles
            modelBuilder.Entity<UserRole>().HasData(
                new UserRole { UserRoleId = 1, RoleName = "MANAGER" },
                new UserRole { UserRoleId = 2, RoleName = "TRAINER" },
                new UserRole { UserRoleId = 3, RoleName = "TRAINEE" }
                );

            // Create user
            modelBuilder.Entity<User>().HasData(
                new User { UserId = 1, UserName = "manager1", UserRoleId = 1, FirstName = "A", LastName = "Nguyễn Văn", Email = "mailA@gmail.com", Password = "1"},
                new User { UserId = 2, UserName = "trainer1", UserRoleId = 2, FirstName = "B", LastName = "Nguyễn Văn", Email = "mailB@gmail.com", Password = "1" },
                new User { UserId = 3, UserName = "trainee1", UserRoleId = 3, FirstName = "C", LastName = "Nguyễn Văn", Email = "mailC@gmail.com", Password = "1" }
                );

            

        }
    }
}
