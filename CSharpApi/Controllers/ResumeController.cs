using CSharpApi.Model;
using CSharpApi.Service;
using Microsoft.AspNetCore.Mvc;

namespace CSharpApi.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class ResumeController : ControllerBase
    {
        [HttpPost("generate")]
        public IActionResult Generate([FromBody] CandidateProfileDto profile)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState); // <-- will show you the exact field causing error
            }

            if (profile == null) return BadRequest("Invalid profile data" + profile);

            var pdfBytes = PdfGenerator.GenerateCandidatePdf(profile);

            return File(pdfBytes, "application/pdf", "CandidateProfile.pdf");
        }
    }
}

