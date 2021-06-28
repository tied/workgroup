const printBanner = () => {
  const bannerArr = []
  bannerArr.push(` ▄▄▌ ▐ ▄▌      ▄▄▄  ▄ •▄  ▄▄ • ▄▄▄        ▄• ▄▌ ▄▄▄· `);
  bannerArr.push(` ██· █▌▐█▪     ▀▄ █·█▌▄▌▪▐█ ▀ ▪▀▄ █·▪     █▪██▌▐█ ▄█ `);
  bannerArr.push(` ██▪▐█▐▐▌ ▄█▀▄ ▐▀▀▄ ▐▀▀▄·▄█ ▀█▄▐▀▀▄  ▄█▀▄ █▌▐█▌ ██▀· `);
  bannerArr.push(` ▐█▌██▐█▌▐█▌.▐▌▐█•█▌▐█.█▌▐█▄▪▐█▐█•█▌▐█▌.▐▌▐█▄█▌▐█▪·• `);
  bannerArr.push(`  ▀▀▀▀ ▀▪ ▀█▄▀▪.▀  ▀·▀  ▀·▀▀▀▀ .▀  ▀ ▀█▄▀▪ ▀▀▀ .▀    `);
  bannerArr.push(`                                   workgroup.js v1.0 `);
  console.log(bannerArr.join('\n'))
}

printBanner()