---
layout: default
title: SSE Rapid Development Weekend
---

Super Space Exterminator
========================

Source Code Repositories
------------------------

<ul class="repos">
    <li><a href="https://github.com/rit-sse/rapdevx">Main</a></li>
    <li><a href="https://github.com/rit-sse/rapdevx-client">Client</a></li>
    <li><a href="https://github.com/rit-sse/rapdevx-gui">Client Resources</a></li>
    <li><a href="https://github.com/rit-sse/rapdevx-server">Server</a></li>
    <li><a href="https://github.com/rit-sse/rapdevx-api">API</a></li>
</ul>


Crash Courses
-------------

<div class="crash">
{% for post in site.categories.ewcc %}
    <article>
        <h1>{{ post.title }}</h1>
        <a href="ewcc/{{ post.ppt }}">Download PowerPoint</a>
        <a href="ewcc/{{ post.pdf }}">Download PDF</a>
    </article>
{% endfor %}
</div>
