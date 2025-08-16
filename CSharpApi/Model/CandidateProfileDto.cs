using System.Text.Json.Serialization;

namespace CSharpApi.Model
{
    public class CandidateProfileDto
    {
        public int UserId { get; set; }
        public string Name { get; set; }
        public string Email { get; set; }
        public DateTime? DateOfBirth { get; set; }
        public string MobileNo { get; set; }
        public string LinkedIn { get; set; }
        public string GitHub { get; set; }

        // Match "cskills" array
        [JsonPropertyName("cskills")]
        public List<SkillDto>? Cskills { get; set; }

        // Match "educationList"
        [JsonPropertyName("educationList")]
        public List<EducationDto>? EducationList { get; set; }

        // Match "experienceList"
        [JsonPropertyName("experienceList")]
        public List<ExperienceDto>? ExperienceList { get; set; }
    }

    public class SkillDto
    {
        public int SkillId { get; set; }         // <-- FE sends skillId
        public string SkillName { get; set; }
    }

    public class EducationDto
    {
        public int EduId { get; set; }           // <-- FE sends eduId
        public string Degree { get; set; }
        public string Grade { get; set; }
        public DateTime? StartYear { get; set; } // FE sends "2013-01-05" → must be DateTime
        public DateTime? EndYear { get; set; }
        public string University { get; set; }
    }

    public class ExperienceDto
    {
        public int ExpId { get; set; }           // <-- FE sends expId
        public string Role { get; set; }
        public string CompanyName { get; set; }
        public DateTime? StartDate { get; set; }
        public DateTime? EndDate { get; set; }
        public double ExpInYears { get; set; }
        public string Description { get; set; }
    }
}

