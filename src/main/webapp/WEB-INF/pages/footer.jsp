<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<style>
    html, body {
        height: 100%;
        margin: 0;
    }
    body {
        display: flex;
        flex-direction: column;
    }
    main {
        flex: 1;
    }
    footer {
        background-color: transparent;
        color: black;
        text-align: center;
        padding: 1rem 0;
        width: 100%;
    }
</style>

<footer>
    <div class="container">
        <span>&copy; <%= java.time.Year.now() %> Stoia Vlad-Eugen</span>
    </div>
</footer>