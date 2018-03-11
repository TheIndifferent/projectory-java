<?xml version="1.0" standalone="no"?>
<svg xmlns="http://www.w3.org/2000/svg" version="1.1"
     width="210mm" height="297mm"
     viewBox="0 0 210 297">

  <g x="15" y="14" width="180" height="268" transform="translate(15,14)">
    <#list cardsRow as row>
      <#list row as card>

        <g x="${card?index * 45}" y="${row?index * 67}" transform="translate(${card?index * 45},${row?index * 67})">
          <rect id="rect1" width="45" height="67" style="fill:none;stroke-width:0.1;stroke:rgb(0,0,0)" x="0" y="0"/>
          <text x="22" y="3" font-family="sans-serif" font-size="5" text-anchor="middle" >
              <#list card.lines as line>
                <tspan x="22" dy="10">
                  ${line}
                </tspan>
              </#list>
          </text>
          <rect width="5" height="5" style="fill:${card.colour}" x="3" y="59"></rect>
          <text width="25" height="5" x="10" y="63" font-family="Consolas" font-size="5">
            ${card.num}
          </text>
        </g>

      </#list>
    </#list>
  </g>
</svg>
