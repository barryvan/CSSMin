# What is it? #

CSSMin takes your CSS file and strips out everything that’s not needed — spaces, extra semicolons, redundant units, and so on. That’s great, but there are loads of programs that do that. A shell script could do that! So what makes CSSMin different?

When you deliver content over the web, best practice is to deliver it gzipped. CSSMin takes your CSS file, and optimises it for gzip compression. This means that there’s a smaller payload delivered over the wire, which results in a faster experience for your users. It does this optimisation by ensuring that the properties inside your selectors are ordered consistently (alphabetically) and in lower case. That way, the gzip algorithm can work at its best.

What this means in practice is that your gzipped CSSMin-ed files are significantly smaller than plain gzipped CSS, and noticeably smaller than files that have been compressed by other means (say, YUI).

# What does it do? #

* Replaces font-weight values like 'bold' with their numeric counterparts;
* Strips quotes wherever possible;
* Changes as much of the contents to lowercase as possible;
* Strips all comments from the source;
* Removes unnecessary whitespace; and, most importantly,
* Reorders the properties within your selectors alphabetically.

# What does it support? #

Just about everything! Nested properties benefit from compression (for example, -webkit-keyframes), whilst retaining the appropriate order. Prefixed selectors are moved to the top of the block, so that their non-prefixed counterparts will override them when the browser supports them. Most CSS hacks won't get stripped, but some involving comments might break.

# Usage #

1. Compile the library:

		# javac CSSMin.java

2. Run your CSS through it:

		# java CSSMin [input] [output]

# Results #

These are the results of compressing the main CSS file for one of the webapps I develop at work. Note that many of these compressors only offer an online service, which means that they can't easily be used as part of your general build process.

<table>
	<thead>
		<tr>
			<td>&nbsp;</td>
			<td>Original size (bytes)</td>
			<td>Gzipped size (bytes)</td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>Plain</td>
			<td>81938</td>
			<td>12291</td>
		</tr>
		<tr>
			<td><a href="http://developer.yahoo.com/yui/compressor/">YUI</a></td>
			<td>64434</td>
			<td>10198</td>
		</tr>
		<tr>
			<td><a href="http://www.lotterypost.com/css-compress.aspx">LotteryPost</a></td>
			<td>63609</td>
			<td>10165</td>
		</tr>
		<tr>
			<td><a href="http://www.cssdrive.com/compressor/compress_advanced.php">CSS Drive</a></td>
			<td>69275</td>
			<td>10795</td>
		</tr>
		<tr>
			<td><a href="https://github.com/barryvan/CSSMin">CSSMin</a></td>
			<td>63791</td>
			<td>9896</td>
		</tr>
	</tbody>
</table>

# Contributors #

CSSMin was originally written, and is maintained by, [Barry van Oudtshoorn](http://www.barryvan.com.au). Bug fixes and contributions have
been made by Kevin de Groote and Pedro Pinheiro.

# More information #

Significant updates warrant an update on [my website](http://barryvan.com.au). [Read the latest entry.](http://www.barryvan.com.au/2011/01/cssmin-updated/)

# License #

CSSMin is licensed under the BSD License. See LICENSE for details.