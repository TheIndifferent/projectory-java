<?xml version="1.0" standalone="no"?>
<svg xmlns="http://www.w3.org/2000/svg" version="1.1"
     width="210mm" height="297mm"
     viewBox="0 0 210 297">

  <g x="15" y="14" width="180" height="268" transform="translate(15,14)">
    <#list cardsRow as row>
      <#list row as card>

        <g x="${card?index * 45}" y="${row?index * 67}" transform="translate(${card?index * 45},${row?index * 67})">
          <rect id="rect1" width="45" height="67" style="fill:none;stroke-width:0.1;stroke:black" x="0" y="0"/>
          <text x="23" y="3" font-family="Helvetica,Arial" font-size="3.8" text-anchor="middle">
              <#list card.lines as line>
                <tspan x="23" dy="5">
                  ${line}
                </tspan>
              </#list>
          </text>
        </g>

      </#list>
    </#list>
  </g>
</svg>
