import{E as r,p as t,s as e,i as a,t as o}from"../vaadin-dev-tools.js";import"construct-style-sheets-polyfill";import"lit";import"lit/decorators.js";import"lit/directives/class-map.js";import"lit/static-html.js";const c={selector:"vaadin-checkbox",displayName:"Checkbox",properties:[{propertyName:"--vaadin-checkbox-size",displayName:"Checkbox size",defaultValue:"var(--lumo-font-size-l)",editorType:r.range,presets:t.lumoFontSize,icon:"square"}]},s={selector:"vaadin-checkbox::part(checkbox)",displayName:"Checkbox box",properties:[e.backgroundColor,e.borderColor,e.borderWidth,e.borderRadius]},i={selector:"vaadin-checkbox[checked]::part(checkbox)",stateAttribute:"checked",displayName:"Checkbox box (when checked)",properties:[e.backgroundColor,e.borderColor,e.borderWidth,e.borderRadius]},p={selector:"vaadin-checkbox::part(checkbox)::after",displayName:"Checkmark",properties:[a.iconColor]},l={selector:"vaadin-checkbox label",displayName:"Label",properties:[o.textColor,o.fontSize,o.fontWeight,o.fontStyle]},x={tagName:"vaadin-checkbox",displayName:"Checkbox",elements:[c,s,i,p,l]};export{s as checkboxElement,i as checkedCheckboxElement,p as checkmarkElement,x as default,c as hostElement,l as labelElement};
