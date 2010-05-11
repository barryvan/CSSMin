# What is it? #

CSSMin is a CSS minifier. It takes your well-written, easy-to-read CSS files, and optimises them for a smaller over-the-wire size.
Although there are a great many CSS minifiers available (including the fantastic YUI), CSSMin has a few special features that allow
it to deliver better over-the-wire sizes than most. The most significant of these is optimisation for gzip.


# Optimising for gzip #

The gzip algorithm works by looking for common strings and swapping them out for shorter versions. (OK, OK, that's a very simplistic
view of how it works; if you're really that interested, check out [the wiki page](http://en.wikipedia.org/wiki/Gzip).) What this means
in practice is that if we can organise our files to maximise the number, and length, of repeating strings, we can achieve better
compression. In terms of CSS files, the simplest way to do this is to order our properties alphabetically. (Clearly, we can't order the
selectors themselves alphabetically, because that could destroy the cascade tree.)


# What sort of results do you achieve? #

Well, here's a chart:
![Comparison of YUI and CSSMin](http://www.barryvan.com.au/projects/cssmin/cssmin-update.png)


# Contributors #

CSSMin was originally written, and is maintained by, [Barry van Oudtshoorn](http://www.barryvan.com.au). Bug fixes and contributions have
been made by Kevin de Groote and Pedro Pinheiro.