<?xml version="1.0" standalone="no"?>
<svg xmlns="http://www.w3.org/2000/svg" version="1.1"
     width="210mm" height="297mm"
     viewBox="0 0 210 297">
  <g x="8" y="10" width="190" height="280" transform="translate(8,10)">

    <#list projects as project>
      <g x="${38 * project?index}" y="0" transform="translate(${38 * project?index},0)">
        <line x1="0" y1="0" x2="0" y2="280" style="stroke:black;stroke-width:0.5"/>
        <rect width="5" height="5" style="fill:${project.colour}" x="3" y="0"/>
        <#list project.cards as card>
          <g x="3" y="${7 + 5 * card?index}" width="5" height="5" transform="translate(3,${7 + 5 * card?index})">
            <!--
            <text x="1" y="4" font-family="monospace" font-size="4" text-anchor="start" fill="${project.colour}">
              ${card?index}:
            </text>
            <text x="7" y="4" font-family="monospace" font-size="4" text-anchor="start" fill="black">
              ${card.result}
            </text>
            -->
            <text x="1" y="4" font-family="monospace" font-size="4" text-anchor="start">
              <tspan style="fill:${project.colour}">
              ${card.num}:
              </tspan>
              <tspan style="fill:black">
              ${card.result}
              </tspan>
            </text>
          </g>
        </#list>
      </g>
    </#list>

  </g>
</svg>
