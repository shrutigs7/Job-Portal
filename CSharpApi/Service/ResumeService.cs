
using QuestPDF.Fluent;
using QuestPDF.Infrastructure;
using CSharpApi.Model;
namespace CSharpApi.Service
{
    public class PdfGenerator
    {
        public static byte[] GenerateCandidatePdf(CandidateProfileDto profile)
        {
            var document = Document.Create(container =>
            {
                container.Page(page =>
                {
                    page.Margin(30);

                    page.Header().Text($"Resume - {profile.Name}")
                        .FontSize(22).Bold();

                    page.Content().Column(col =>
                    {
                        // Basic Info
                        col.Item().Text($"Email: {profile.Email}");
                        col.Item().Text($"Phone: {profile.MobileNo}");
                        col.Item().Text($"Date of Birth: {profile.DateOfBirth:dd MMM yyyy}");
                        col.Item().Text($"LinkedIn: {profile.LinkedIn}");
                        col.Item().Text($"GitHub: {profile.GitHub}");

                        col.Item().PaddingVertical(10).LineHorizontal(1);

                        // Skills
                        if (profile.Cskills?.Any() == true)
                        {
                            col.Item().Text("Skills").FontSize(16).Bold();
                            foreach (var skill in profile.Cskills)
                            {
                                col.Item().Text($"- {skill.SkillName}");
                            }
                        }

                        col.Item().PaddingVertical(10).LineHorizontal(1);

                        // Education
                        if (profile.EducationList?.Any() == true)
                        {
                            col.Item().Text("Education").FontSize(16).Bold();
                            foreach (var edu in profile.EducationList)
                            {

                                col.Item().Text($"{edu.Degree} ({edu.StartYear.Value.Year}-{edu.EndYear.Value.Year}) - {edu.University}, Grade: {edu.Grade}");
                            }
                        }

                        col.Item().PaddingVertical(10).LineHorizontal(1);

                        // Experience
                        if (profile.ExperienceList?.Any() == true)
                        {
                            col.Item().Text("Experience").FontSize(16).Bold();
                            foreach (var exp in profile.ExperienceList)
                            {
                                col.Item().Text($"{exp.Role} at {exp.CompanyName} ({exp.StartDate:MMM yyyy} - {exp.EndDate:MMM yyyy})");
                                col.Item().Text($"Duration: {exp.ExpInYears} years");
                                col.Item().Text(exp.Description);
                            }
                        }
                    });
                });
            });

            return document.GeneratePdf();
        }
    }
}
