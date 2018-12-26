<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getName()
    isAdmin = user.isAdmin()
    isUser = user.isUser()
    >
<#else>
    <#assign
    name = "unknown"
    isAdmin = false
    isUser = false
    >
</#if>