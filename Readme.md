通过Bitmap构造函数参数调整来实现和Flutter BoxFit枚举对应的压缩效果

例图上半部分通过bitmap API实现，下半部分通过调整ImageView的scaleType实现

具体参考BitmapBoxFit.kt内容

 <div align=center>
<table>
<tr>
<td>
<img src="example/cover.png" width=300 alt=""/>
<p align=center><font size="1" color="grey">cover</font></p>
</td>
<td>
<img src="example/fill.png" width=300 alt=""/>
<p align=center><font size="1" color="grey">fill</font></p>
</td>
<td>
<img src="example/contains.png" width=300 alt=""/>
<p align=center><font size="1" color="grey">contains</font></p>
</td>
</tr>
</table>
</div>

示例代码：

	 bitmap.boxFitFill(width, height)
	 bitmap.boxFitCover(width, height)
	 bitmap.boxFitFitHeight(height)




