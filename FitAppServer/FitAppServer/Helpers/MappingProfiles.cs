using AutoMapper;
using FitAppServer.DTOs;
using FitAppServer.Models;
using System.Net;

namespace FitAppServer.Helpers
{
    public class MappingProfiles : Profile
    {
        public MappingProfiles()
        {
            CreateMap<User, UserRegisterDTO>();
            CreateMap<UserRegisterDTO, User>();

        }
    }
}
