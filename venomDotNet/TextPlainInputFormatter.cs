using Microsoft.AspNetCore.Mvc.Formatters;
using Microsoft.Net.Http.Headers;

namespace VenomDotNet;

public class TextPlainInputFormatter : InputFormatter
{
    public TextPlainInputFormatter()
    {
        SupportedMediaTypes.Add(MediaTypeHeaderValue.Parse("text/plain"));
    }

    public override async Task<InputFormatterResult> ReadRequestBodyAsync(InputFormatterContext context)
    {
        var request = context.HttpContext.Request;
        using (var reader = new StreamReader(request.Body))
        {
            var content = await reader.ReadToEndAsync();
            return InputFormatterResult.Success(content);
        }
    }

    public override bool CanRead(InputFormatterContext context)
    {
        var contentType = context.HttpContext.Request.ContentType;
        return contentType.StartsWith("text/plain", StringComparison.OrdinalIgnoreCase);
    }
}