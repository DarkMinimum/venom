using Microsoft.AspNetCore.Mvc;
using org.matheval;

namespace VenomDotNet.Controllers;

[ApiController]
[Route("")]
public class CalculationController : ControllerBase
{
    private readonly ILogger<CalculationController> _logger;

    public CalculationController(ILogger<CalculationController> logger)
    {
        _logger = logger;
    }

    [HttpPost]
    [Consumes("text/plain")]
    public string Get([FromBody] string stringExpression)
    {
        try
        {
            return new Expression(stringExpression.Split("=")[1]).Eval().ToString();
        }
        catch (Exception ex)
        {
            return "wrong expression";
        }
    }
}