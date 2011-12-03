---
layout: default
title: SSE Rapid Development Weekend
---

Super Space Exterminator
========================

<div class="repos">
    <h2>Source Code Repositories</h2>
    <ul>
        <li><a href="https://github.com/rit-sse/rapdevx">Main</a></li>
        <li><a href="https://github.com/rit-sse/rapdevx-client">Client</a></li>
        <li><a href="https://github.com/rit-sse/rapdevx-gui">Client Resources</a></li>
        <li><a href="https://github.com/rit-sse/rapdevx-server">Server</a></li>
        <li><a href="https://github.com/rit-sse/rapdevx-api">API</a></li>
    </ul>
</div>


<div class="crash">
    <h2>Epic Week of Crash Courses</h2>
{% for post in site.categories.ewcc %}
    <article>
        <h1>{{ post.title }}</h1>
        <a href="ewcc/{{ post.ppt }}">Download PowerPoint</a>
        <a href="ewcc/{{ post.pdf }}">Download PDF</a>
    </article>
{% endfor %}
</div>
