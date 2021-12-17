INSERT INTO language_data_availability_count
    (source_language_id, target_language_id, content_type_id, domain_id, word_count)
VALUES
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '594812014'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '495829671'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '402755241'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '336736426'),
    ((select l.id
        from language l
        where l.tag = 'ar'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '290959959'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '282399223'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '274142560'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '269230388'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '267533585'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '266937908'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '243106991'),
    ((select l.id
        from language l
        where l.tag = 'en-AU'), (select l.id
        from language l
        where l.tag = 'fr-CA'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '214938654'),
    ((select l.id
        from language l
        where l.tag = 'es'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '200381263'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '199604637'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '187015910'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '175218374'),
    ((select l.id
        from language l
        where l.tag = 'ru-RU'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '173883252'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '168128821'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '166008139'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '164146406'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '159721075'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '149083126'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ar'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '137104671'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ar'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '134693420'),
    ((select l.id
        from language l
        where l.tag = 'fr-BE'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Energy - Water & Utilities' and origin = 'MARKETPLACE'), '134238243'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '130436272'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'nl-BE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Energy - Water & Utilities' and origin = 'MARKETPLACE'), '127414187'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'ar'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '119987179'),
    ((select l.id
        from language l
        where l.tag = 'es'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '118843806'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '117720948'),
    ((select l.id
        from language l
        where l.tag = 'es'), (select l.id
        from language l
        where l.tag = 'nl-BE'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '113539894'),
    ((select l.id
        from language l
        where l.tag = 'ar'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '107897442'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'ar'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '106559665'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '105961275'),
    ((select l.id
        from language l
        where l.tag = 'cs-CZ'), (select l.id
        from language l
        where l.tag = 'ar'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '105554375'),
    ((select l.id
        from language l
        where l.tag = 'es'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '104553170'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '103249759'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '102532455'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '97176525'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '96936814'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '95985861'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '94693234'),
    ((select l.id
        from language l
        where l.tag = 'nl-BE'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '92655588'),
    ((select l.id
        from language l
        where l.tag = 'ar'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '92343938'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '91408859'),
    ((select l.id
        from language l
        where l.tag = 'en-CA'), (select l.id
        from language l
        where l.tag = 'fr-CA'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '90816841'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '89947727'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '87867943'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '87775008'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '87608970'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '86759619'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'hr-HR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '85124482'),
    ((select l.id
        from language l
        where l.tag = 'ar'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '84676354'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '81111831'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '80009377'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'nl-BE'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '78659415'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '78059028'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '77925086'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '77838028'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '77697717'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '77649408'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '77264486'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '76481037'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '75581284'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '74975496'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '73760212'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'tr-TR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '72064705'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '71386809'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'en-CA'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '71067887'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '68446804'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'id-ID'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '65960549'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '64079773'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '63812869'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '62880558'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '61377999'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-CA'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '59293160'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '59097833'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '56710194'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Retail & Wholesale Distribution/E-Commerce' and origin = 'MARKETPLACE'), '56039837'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '56009981'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '55927331'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '55359892'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '54302335'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '52795131'),
    ((select l.id
        from language l
        where l.tag = 'fr-CA'), (select l.id
        from language l
        where l.tag = 'nl-BE'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '52659867'),
    ((select l.id
        from language l
        where l.tag = 'es'), (select l.id
        from language l
        where l.tag = 'fr-CA'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '52140639'),
    ((select l.id
        from language l
        where l.tag = 'fr-CA'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '52000518'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '51410977'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '51250393'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '51033058'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '51020208'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '50890515'),
    ((select l.id
        from language l
        where l.tag = 'en-AU'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '50475588'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '50374688'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '50373448'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '50371553'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '50351069'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '50295386'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '50243440'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '50237092'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '50199528'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '50146913'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '50019659'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '49992381'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '49899335'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '49887616'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '49812570'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '49739784'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '49704560'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '49312732'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '49282831'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '49028189'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '48975939'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '48936454'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '48895522'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '48830537'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '48755541'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '48640671'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '48513986'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '48026679'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '47904197'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '47847761'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '47234620'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '47153616'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '46956271'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '46880973'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '46843008'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '46839823'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '46826220'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '46822346'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '46809636'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '46775353'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '46732965'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '46653154'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '46525471'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '46475847'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '46467862'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '46449840'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Energy - Water & Utilities' and origin = 'MARKETPLACE'), '46356738'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '46201257'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '46002698'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'fr-CA'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '45416319'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '45283605'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'en-CA'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '45272361'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '45038516'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'fr-CA'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '45002284'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '44878499'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '44866282'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '44836552'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '44621970'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '44621289'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '44470791'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '44293837'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '44209428'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '44065938'),
    ((select l.id
        from language l
        where l.tag = 'nl-BE'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '43759828'),
    ((select l.id
        from language l
        where l.tag = 'pt-BR'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '43564247'),
    ((select l.id
        from language l
        where l.tag = 'fr-BE'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Energy - Water & Utilities' and origin = 'MARKETPLACE'), '43344214'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ko-KR'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '42652591'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '42128222'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '41872912'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '41172117'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '40946888'),
    ((select l.id
        from language l
        where l.tag = 'ar'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '40677248'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '40608570'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '40402324'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '40356014'),
    ((select l.id
        from language l
        where l.tag = 'pl-PL'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '40353845'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '40286890'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '40276189'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '40262669'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '40236735'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '40109991'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '40102874'),
    ((select l.id
        from language l
        where l.tag = 'sk-SK'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '39968795'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '39964656'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '39842096'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '39841355'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '39800765'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '39743080'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '39668022'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '39661051'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '39600523'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '39564053'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '39544541'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '39531997'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '39510928'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '39494658'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '39225407'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '39177835'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '39154275'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '39057796'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '39008026'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '39006500'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '39000656'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '38995107'),
    ((select l.id
        from language l
        where l.tag = 'sl-SI'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '38984284'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '38940481'),
    ((select l.id
        from language l
        where l.tag = 'cs-CZ'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '38935127'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '38778792'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '38759899'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '38701280'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '38679790'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '38655415'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '37947472'),
    ((select l.id
        from language l
        where l.tag = 'hu-HU'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '37750156'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '37549576'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '37452228'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '37318640'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '36978887'),
    ((select l.id
        from language l
        where l.tag = 'cs-CZ'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '36124223'),
    ((select l.id
        from language l
        where l.tag = 'lv-LV'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '35954915'),
    ((select l.id
        from language l
        where l.tag = 'lt-LT'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '35921932'),
    ((select l.id
        from language l
        where l.tag = 'fr-CA'), (select l.id
        from language l
        where l.tag = 'en-CA'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '35898348'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '35841138'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '35381602'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '35349859'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '34942069'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '34746485'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '34566496'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '34499411'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '34415477'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-CA'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '34406112'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '34155587'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '33881333'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '33715145'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-CA'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '32745498'),
    ((select l.id
        from language l
        where l.tag = 'ar'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '32709087'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '31702958'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '31644967'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '31642075'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '31499572'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '31359947'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '31331617'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '31307605'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '31302396'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '31291195'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '31201306'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '31190451'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '31121014'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '31087796'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '31057439'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '31039819'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '31028075'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '31020379'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '30987273'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '30984682'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '30960154'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '30930796'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '30926796'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '30917357'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '30847368'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '30822214'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '30811643'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '30656708'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '30642951'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '30641321'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '30629057'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '30333115'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'mt-MT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '29986131'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'nl-BE'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '29951173'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '29550830'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '29357318'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '29355882'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '29355416'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '29287728'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'mt-MT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '29047728'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '28969319'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '28936147'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '28882885'),
    ((select l.id
        from language l
        where l.tag = 'es'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '28856366'),
    ((select l.id
        from language l
        where l.tag = 'en-CA'), (select l.id
        from language l
        where l.tag = 'fr-CA'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '28607515'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '28594185'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'fr-BE'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '28201868'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '28070019'),
    ((select l.id
        from language l
        where l.tag = 'sv-SE'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '27943325'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '27714785'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '27699248'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'mt-MT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '27465234'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '27407312'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '27242806'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '26957507'),
    ((select l.id
        from language l
        where l.tag = 'ro-RO'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '26810318'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'mt-MT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '26672343'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '26611563'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '26265809'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '26108433'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'mt-MT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '26104470'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '26032402'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '25930327'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-TW'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '25887131'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '25713877'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '25619333'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '25604562'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '25589941'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '25551671'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '25551127'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '25525934'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '25496247'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '25476534'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '25474769'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '25472382'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '25468955'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '25450101'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '25414284'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '25403293'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '25397900'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '25347742'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '25279128'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '25277676'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '25187867'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '25126180'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '25121485'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '24815346'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '24809530'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '24517442'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '24392965'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'mt-MT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '23887566'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '23847196'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '23685860'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '23384278'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'mt-MT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '23271424'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '22912191'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '22662566'),
    ((select l.id
        from language l
        where l.tag = 'mt-MT'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '22645516'),
    ((select l.id
        from language l
        where l.tag = 'sv-SE'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '22342053'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '22336980'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '22327051'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '22043435'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '21540653'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Energy - Water & Utilities' and origin = 'MARKETPLACE'), '21010946'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '20951348'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '20518306'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '20386992'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'nl-BE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '20295088'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '20059171'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '19684699'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '19428152'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '19402803'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '19142011'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '19083844'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18975645'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18912816'),
    ((select l.id
        from language l
        where l.tag = 'fr-CA'), (select l.id
        from language l
        where l.tag = 'en-CA'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '18879517'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '18846800'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18834152'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18825889'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '18818878'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18785186'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '18766553'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18737594'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'mt-MT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18708443'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'mt-MT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18707740'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18605818'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18596886'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18538214'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '18532580'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '18471060'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18432713'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18388285'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18387640'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'mt-MT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18333162'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18294992'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18285203'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18248291'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18193729'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18173312'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'mt-MT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18155056'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18153363'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18150368'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '18110137'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '18092398'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '18083436'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'mt-MT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '18017386'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17994994'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17951718'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '17881688'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17856746'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17790796'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17724758'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17724663'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-TW'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '17688385'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'mt-MT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17687620'),
    ((select l.id
        from language l
        where l.tag = 'sv-SE'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17637159'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'mt-MT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17613943'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17610699'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '17553646'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17491007'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17455485'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17376727'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17356585'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17355529'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17331558'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17296039'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17294731'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17254288'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17192685'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17171988'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17171309'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17145812'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17145501'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17121820'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17114396'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17094600'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17072585'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '17000207'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '16972687'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '16961295'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '16950348'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '16892254'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '16813093'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '16764636'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '16728725'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '16672493'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '16437987'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '16395295'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '16228801'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '16212978'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '16057984'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '15974662'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '15964467'),
    ((select l.id
        from language l
        where l.tag = 'en-CA'), (select l.id
        from language l
        where l.tag = 'fr-CA'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '15621399'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '15559797'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '15332662'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '15318191'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '15302030'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '15032309'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '14842537'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '14828181'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '14783652'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'hr-HR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '14779928'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '14673888'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '14673641'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '14613619'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '14306861'),
    ((select l.id
        from language l
        where l.tag = 'fr-CA'), (select l.id
        from language l
        where l.tag = 'en-CA'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '14195907'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '14083311'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'hr-HR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '14041162'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '13991599'),
    ((select l.id
        from language l
        where l.tag = 'cs-CZ'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '13977168'),
    ((select l.id
        from language l
        where l.tag = 'sk-SK'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '13933453'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '13932527'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'hr-HR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '13900526'),
    ((select l.id
        from language l
        where l.tag = 'sk-SK'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '13646854'),
    ((select l.id
        from language l
        where l.tag = 'sl-SI'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '13591740'),
    ((select l.id
        from language l
        where l.tag = 'sl-SI'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '13512417'),
    ((select l.id
        from language l
        where l.tag = 'ar-AE'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '13467100'),
    ((select l.id
        from language l
        where l.tag = 'sl-SI'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '13390417'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'es-MX'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '13346133'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '13323380'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '13269579'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '13260233'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '13152316'),
    ((select l.id
        from language l
        where l.tag = 'es'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '13072162'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '13060940'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '12807454'),
    ((select l.id
        from language l
        where l.tag = 'pl-PL'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '12732634'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '12722940'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '12644417'),
    ((select l.id
        from language l
        where l.tag = 'pl-PL'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '12631946'),
    ((select l.id
        from language l
        where l.tag = 'pl-PL'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '12597560'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '12587085'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '12548846'),
    ((select l.id
        from language l
        where l.tag = 'pl-PL'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '12535071'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '12517365'),
    ((select l.id
        from language l
        where l.tag = 'sl-SI'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '12490802'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '12424234'),
    ((select l.id
        from language l
        where l.tag = 'lv-LV'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '12399595'),
    ((select l.id
        from language l
        where l.tag = 'lv-LV'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '12382737'),
    ((select l.id
        from language l
        where l.tag = 'sk-SK'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '12355570'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '12186346'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '12172415'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '12021070'),
    ((select l.id
        from language l
        where l.tag = 'hu-HU'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '11859247'),
    ((select l.id
        from language l
        where l.tag = 'pl-PL'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '11798819'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '11482231'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '11305775'),
    ((select l.id
        from language l
        where l.tag = 'sl-SI'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '11270550'),
    ((select l.id
        from language l
        where l.tag = 'sl-SI'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '11255188'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '11252989'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '11239419'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '11205778'),
    ((select l.id
        from language l
        where l.tag = 'hu-HU'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '11197027'),
    ((select l.id
        from language l
        where l.tag = 'cs-CZ'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '11175056'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '11017182'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '10622327'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '10582434'),
    ((select l.id
        from language l
        where l.tag = 'lv-LV'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '10568486'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '10520995'),
    ((select l.id
        from language l
        where l.tag = 'lv-LV'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '10502827'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '10320540'),
    ((select l.id
        from language l
        where l.tag = 'pl-PL'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '10267509'),
    ((select l.id
        from language l
        where l.tag = 'fr-CA'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '10201554'),
    ((select l.id
        from language l
        where l.tag = 'es'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '10018667'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '9907800'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '9883645'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '9698075'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '9695387'),
    ((select l.id
        from language l
        where l.tag = 'es'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '9677528'),
    ((select l.id
        from language l
        where l.tag = 'nl-BE'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '9628816'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '9617416'),
    ((select l.id
        from language l
        where l.tag = 'ro-RO'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '9583275'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Retail & Wholesale Distribution/E-Commerce' and origin = 'MARKETPLACE'), '9564180'),
    ((select l.id
        from language l
        where l.tag = 'ro-RO'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '9404261'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '9357422'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '9320527'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '9301655'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '9292665'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '9281617'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '9226809'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '9226232'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '9219440'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '9183307'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '9161933'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '9158657'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '9155311'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '9149995'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '9148929'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '9142109'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '9111673'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '9051676'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '9049821'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '9049045'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '9002396'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '8809301'),
    ((select l.id
        from language l
        where l.tag = 'hu-HU'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '8795699'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '8752894'),
    ((select l.id
        from language l
        where l.tag = 'sk-SK'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '8675895'),
    ((select l.id
        from language l
        where l.tag = 'es'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Pharmaceuticals & Biotechnology' and origin = 'MARKETPLACE'), '8668302'),
    ((select l.id
        from language l
        where l.tag = 'ar'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '8622363'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '8609444'),
    ((select l.id
        from language l
        where l.tag = 'es'), (select l.id
        from language l
        where l.tag = 'zh-HK'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '8592572'),
    ((select l.id
        from language l
        where l.tag = 'es'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Retail & Wholesale Distribution/E-Commerce' and origin = 'MARKETPLACE'), '8574277'),
    ((select l.id
        from language l
        where l.tag = 'es'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '8567442'),
    ((select l.id
        from language l
        where l.tag = 'es'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '8520387'),
    ((select l.id
        from language l
        where l.tag = 'sl-SI'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '8502251'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '8501597'),
    ((select l.id
        from language l
        where l.tag = 'sl-SI'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '8461253'),
    ((select l.id
        from language l
        where l.tag = 'ro-RO'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '8433062'),
    ((select l.id
        from language l
        where l.tag = 'es'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '8415705'),
    ((select l.id
        from language l
        where l.tag = 'ro-RO'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '8407157'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '8346824'),
    ((select l.id
        from language l
        where l.tag = 'es'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Retail & Wholesale Distribution/E-Commerce' and origin = 'MARKETPLACE'), '8334991'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '8332599'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '8239813'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'hr-HR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '8214172'),
    ((select l.id
        from language l
        where l.tag = 'sk-SK'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '8208280'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '8180343'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '8154999'),
    ((select l.id
        from language l
        where l.tag = 'lv-LV'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '8139750'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '8127786'),
    ((select l.id
        from language l
        where l.tag = 'pl-PL'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '8103087'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Energy - Water & Utilities' and origin = 'MARKETPLACE'), '8079973'),
    ((select l.id
        from language l
        where l.tag = 'pl-PL'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '8076057'),
    ((select l.id
        from language l
        where l.tag = 'pl-PL'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '8056205'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'hr-HR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '8028335'),
    ((select l.id
        from language l
        where l.tag = 'sl-SI'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '8022422'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '7974336'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '7930065'),
    ((select l.id
        from language l
        where l.tag = 'cs-CZ'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '7927821'),
    ((select l.id
        from language l
        where l.tag = 'lt-LT'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '7927815'),
    ((select l.id
        from language l
        where l.tag = 'pl-PL'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '7922639'),
    ((select l.id
        from language l
        where l.tag = 'ro-RO'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '7921722'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '7887641'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '7867027'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Energy - Water & Utilities' and origin = 'MARKETPLACE'), '7854227'),
    ((select l.id
        from language l
        where l.tag = 'cs-CZ'), (select l.id
        from language l
        where l.tag = 'en-CA'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '7839410'),
    ((select l.id
        from language l
        where l.tag = 'lt-LT'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '7838498'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '7801109'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Energy - Water & Utilities' and origin = 'MARKETPLACE'), '7789140'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '7779141'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '7754673'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '7711469'),
    ((select l.id
        from language l
        where l.tag = 'cs-CZ'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '7704594'),
    ((select l.id
        from language l
        where l.tag = 'lt-LT'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '7685356'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'hr-HR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '7574379'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '7503621'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '7500527'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '7478124'),
    ((select l.id
        from language l
        where l.tag = 'hu-HU'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '7456221'),
    ((select l.id
        from language l
        where l.tag = 'hu-HU'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '7387746'),
    ((select l.id
        from language l
        where l.tag = 'lv-LV'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '7327024'),
    ((select l.id
        from language l
        where l.tag = 'cs-CZ'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '7315641'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'hr-HR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '7308753'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '7292283'),
    ((select l.id
        from language l
        where l.tag = 'pl-PL'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '7287798'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '7270015'),
    ((select l.id
        from language l
        where l.tag = 'lt-LT'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '7259718'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'hr-HR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '7224273'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '7208954'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '7198000'),
    ((select l.id
        from language l
        where l.tag = 'cs-CZ'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '7127649'),
    ((select l.id
        from language l
        where l.tag = 'lv-LV'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '7106157'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'mt-MT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '7104247'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '7063880'),
    ((select l.id
        from language l
        where l.tag = 'ro-RO'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '7059893'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '7046820'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'hr-HR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '7045986'),
    ((select l.id
        from language l
        where l.tag = 'hu-HU'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '6979173'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '6973751'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '6888640'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6874664'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '6850399'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6849610'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '6842025'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Energy - Water & Utilities' and origin = 'MARKETPLACE'), '6835315'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6830318'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6826144'),
    ((select l.id
        from language l
        where l.tag = 'ar-EG'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '6825790'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6814724'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6796405'),
    ((select l.id
        from language l
        where l.tag = 'sv-SE'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '6794345'),
    ((select l.id
        from language l
        where l.tag = 'ro-RO'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '6789152'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6785142'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6779904'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6776961'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '6765071'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6761991'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '6757659'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '6747840'),
    ((select l.id
        from language l
        where l.tag = 'pt-BR'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '6747840'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6747835'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '6744483'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '6718541'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'mt-MT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6714672'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6674912'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '6615929'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '6588519'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6586191'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6582451'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'hr-HR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6567785'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '6558896'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '6529628'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ko-KR'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '6516011'),
    ((select l.id
        from language l
        where l.tag = 'cs-CZ'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '6496596'),
    ((select l.id
        from language l
        where l.tag = 'hr-HR'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6482152'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6449081'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6438660'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '6413200'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'nl-BE'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '6402789'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6387248'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'hr-HR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6384288'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6383452'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6376523'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '6373577'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6363345'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '6356116'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6350207'),
    ((select l.id
        from language l
        where l.tag = 'sk-SK'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '6340156'),
    ((select l.id
        from language l
        where l.tag = 'lt-LT'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '6328693'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6326259'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6325288'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Pharmaceuticals & Biotechnology' and origin = 'MARKETPLACE'), '6308360'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6304542'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '6298572'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '6296207'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '6287605'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '6266213'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6262305'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6255383'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6244292'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6235634'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6215579'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '6209532'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6208382'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '6181230'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '6165413'),
    ((select l.id
        from language l
        where l.tag = 'sv-SE'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '6160539'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'nl-BE'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '6144423'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6101820'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '6052096'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '6050235'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '6023579'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5992462'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'nl-BE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '5991096'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '5961520'),
    ((select l.id
        from language l
        where l.tag = 'ja-JP'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5928352'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '5911750'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Pharmaceuticals & Biotechnology' and origin = 'MARKETPLACE'), '5904634'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5894514'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'hr-HR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '5890679'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5886414'),
    ((select l.id
        from language l
        where l.tag = 'ar'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5839089'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '5763025'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5752791'),
    ((select l.id
        from language l
        where l.tag = 'ar'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5740776'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5733659'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '5728252'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '5725470'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '5725071'),
    ((select l.id
        from language l
        where l.tag = 'ar'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '5720887'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '5719667'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '5709287'),
    ((select l.id
        from language l
        where l.tag = 'hu-HU'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5690811'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '5678983'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5674851'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '5674431'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5670709'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5668241'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '5661171'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '5646364'),
    ((select l.id
        from language l
        where l.tag = 'fr-CA'), (select l.id
        from language l
        where l.tag = 'en-CA'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5641559'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '5636223'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '5625095'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '5623069'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '5620842'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '5619290'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5614220'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '5608954'),
    ((select l.id
        from language l
        where l.tag = 'ro-RO'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5587750'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '5582111'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '5563907'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'en-CA'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '5558318'),
    ((select l.id
        from language l
        where l.tag = 'hu-HU'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5539963'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '5513318'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '5476162'),
    ((select l.id
        from language l
        where l.tag = 'sl-SI'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5471426'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5461264'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5453839'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '5433651'),
    ((select l.id
        from language l
        where l.tag = 'mt-MT'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '5429851'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5429774'),
    ((select l.id
        from language l
        where l.tag = 'sv-SE'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5394334'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5386382'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'vi-VN'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5361840'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '5339429'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '5332648'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '5327017'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '5310919'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '5269085'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '5254409'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '5226612'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '5194105'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '5191355'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5163629'),
    ((select l.id
        from language l
        where l.tag = 'lv-LV'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5162475'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'nl-BE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '5157689'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'hr-HR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '5148120'),
    ((select l.id
        from language l
        where l.tag = 'sv-SE'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5146781'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '5142090'),
    ((select l.id
        from language l
        where l.tag = 'lt-LT'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5130681'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '5121356'),
    ((select l.id
        from language l
        where l.tag = 'sv-SE'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5102635'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '5099909'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '5073815'),
    ((select l.id
        from language l
        where l.tag = 'ro-RO'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5049415'),
    ((select l.id
        from language l
        where l.tag = 'pl-PL'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5019344'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'mt-MT'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '4983227'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '4977770'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4970370'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'hr-HR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '4969342'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '4924969'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '4917299'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ko-KR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '4905888'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4893122'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4889904'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4879775'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '4867186'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '4865538'),
    ((select l.id
        from language l
        where l.tag = 'hu-HU'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4830397'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4830310'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '4816784'),
    ((select l.id
        from language l
        where l.tag = 'ja-JP'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '4804229'),
    ((select l.id
        from language l
        where l.tag = 'lt-LT'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4800292'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '4789643'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '4774163'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '4720630'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '4716416'),
    ((select l.id
        from language l
        where l.tag = 'sl-SI'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4701896'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4701808'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '4700004'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '4699854'),
    ((select l.id
        from language l
        where l.tag = 'nl-BE'), (select l.id
        from language l
        where l.tag = 'fr-BE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '4685211'),
    ((select l.id
        from language l
        where l.tag = 'pl-PL'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4670675'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '4656333'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '4647391'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '4634783'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ko-KR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '4622702'),
    ((select l.id
        from language l
        where l.tag = 'sl-SI'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4594539'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '4593501'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '4589074'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '4575140'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '4574054'),
    ((select l.id
        from language l
        where l.tag = 'sk-SK'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4563964'),
    ((select l.id
        from language l
        where l.tag = 'pl-PL'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4543465'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '4539324'),
    ((select l.id
        from language l
        where l.tag = 'hu-HU'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4522955'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '4519886'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '4517590'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4513887'),
    ((select l.id
        from language l
        where l.tag = 'en-CA'), (select l.id
        from language l
        where l.tag = 'fr-CA'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4495455'),
    ((select l.id
        from language l
        where l.tag = 'lv-LV'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4463870'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'ar-AE'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '4436972'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4434321'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '4412520'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4383484'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '4370747'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '4362013'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4342647'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '4341353'),
    ((select l.id
        from language l
        where l.tag = 'pl-PL'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4334785'),
    ((select l.id
        from language l
        where l.tag = 'lt-LT'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4332605'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '4314223'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '4313229'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '4299072'),
    ((select l.id
        from language l
        where l.tag = 'lv-LV'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4292128'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '4277863'),
    ((select l.id
        from language l
        where l.tag = 'ar'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '4269133'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '4267234'),
    ((select l.id
        from language l
        where l.tag = 'en-ZA'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '4229552'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4227547'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4214413'),
    ((select l.id
        from language l
        where l.tag = 'lt-LT'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4188397'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4188336'),
    ((select l.id
        from language l
        where l.tag = 'cs-CZ'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4184250'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4180616'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '4174279'),
    ((select l.id
        from language l
        where l.tag = 'ro-RO'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4169604'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '4166845'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '4159511'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4158063'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '4146421'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '4139793'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4138454'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4138097'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ko-KR'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '4133134'),
    ((select l.id
        from language l
        where l.tag = 'sv-SE'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4129346'),
    ((select l.id
        from language l
        where l.tag = 'sv-SE'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4125063'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '4109121'),
    ((select l.id
        from language l
        where l.tag = 'cs-CZ'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '4100667'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '4088205'),
    ((select l.id
        from language l
        where l.tag = 'ro-RO'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4072553'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '4071480'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '4032468'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '4028554'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-MX'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '4020680'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4007329'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '3988108'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '3985720'),
    ((select l.id
        from language l
        where l.tag = 'hu-HU'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3976606'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3967260'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3965103'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'mt-MT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '3942962'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3940846'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3887070'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3882343'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3875397'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Pharmaceuticals & Biotechnology' and origin = 'MARKETPLACE'), '3847626'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3834509'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3832285'),
    ((select l.id
        from language l
        where l.tag = 'es'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3829362'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3820943'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '3806686'),
    ((select l.id
        from language l
        where l.tag = 'ro-RO'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '3802987'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '3781865'),
    ((select l.id
        from language l
        where l.tag = 'pl-PL'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3776802'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3768623'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3761977'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3744985'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '3721201'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '3712769'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3702553'),
    ((select l.id
        from language l
        where l.tag = 'sk-SK'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3699160'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3682453'),
    ((select l.id
        from language l
        where l.tag = 'sv-SE'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '3679419'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3675517'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Pharmaceuticals & Biotechnology' and origin = 'MARKETPLACE'), '3664849'),
    ((select l.id
        from language l
        where l.tag = 'hu-HU'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3662152'),
    ((select l.id
        from language l
        where l.tag = 'id-ID'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3647363'),
    ((select l.id
        from language l
        where l.tag = 'sv-SE'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3628184'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '3619624'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '3606190'),
    ((select l.id
        from language l
        where l.tag = 'ar'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '3596513'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'tr-TR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3529500'),
    ((select l.id
        from language l
        where l.tag = 'ro-RO'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3517667'),
    ((select l.id
        from language l
        where l.tag = 'sl-SI'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3511002'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '3485665'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '3474646'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3469745'),
    ((select l.id
        from language l
        where l.tag = 'lt-LT'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Retail & Wholesale Distribution/E-Commerce' and origin = 'MARKETPLACE'), '3467206'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3463191'),
    ((select l.id
        from language l
        where l.tag = 'lv-LV'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '3430717'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '3390041'),
    ((select l.id
        from language l
        where l.tag = 'ro-RO'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3338051'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3335084'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Pharmaceuticals & Biotechnology' and origin = 'MARKETPLACE'), '3320462'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '3300470'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '3296933'),
    ((select l.id
        from language l
        where l.tag = 'tr-TR'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '3229844'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '3217508'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '3211247'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '3209489'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '3198231'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '3169534'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-MX'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '3160695'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3158627'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '3156804'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '3144089'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3130138'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '3111580'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '3110950'),
    ((select l.id
        from language l
        where l.tag = 'sk-SK'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3052392'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-MX'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '3041203'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '3036609'),
    ((select l.id
        from language l
        where l.tag = 'cs-CZ'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '3034353'),
    ((select l.id
        from language l
        where l.tag = 'cs-CZ'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3026735'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '3009332'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '2992006'),
    ((select l.id
        from language l
        where l.tag = 'et-EE'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '2987829'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '2986165'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '2955787'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '2950848'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '2941624'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'uk-UA'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '2915643'),
    ((select l.id
        from language l
        where l.tag = 'sk-SK'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '2902621'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '2895226'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-TW'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '2871157'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '2826422'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '2826137'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '2822367'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '2803398'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-CA'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '2779261'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'ar-EG'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '2743825'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '2731344'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '2701703'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '2685622'),
    ((select l.id
        from language l
        where l.tag = 'ro-RO'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Retail & Wholesale Distribution/E-Commerce' and origin = 'MARKETPLACE'), '2660403'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '2635107'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Retail & Wholesale Distribution/E-Commerce' and origin = 'MARKETPLACE'), '2605038'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '2586849'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '2541519'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '2541223'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '2518190'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '2507490'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '2504816'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ar'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '2497346'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-MX'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '2485364'),
    ((select l.id
        from language l
        where l.tag = 'ar'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '2475485'),
    ((select l.id
        from language l
        where l.tag = 'sv-SE'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '2442533'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ko-KR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '2426588'),
    ((select l.id
        from language l
        where l.tag = 'hu-HU'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '2414068'),
    ((select l.id
        from language l
        where l.tag = 'hu-HU'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '2398662'),
    ((select l.id
        from language l
        where l.tag = 'sk-SK'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '2396944'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '2386157'),
    ((select l.id
        from language l
        where l.tag = 'sl-SI'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '2371425'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '2367822'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '2362989'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '2362949'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '2355711'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '2329234'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'no-NO'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '2315908'),
    ((select l.id
        from language l
        where l.tag = 'ro-RO'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '2306293'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '2304548'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '2300739'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-TW'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '2292831'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '2283989'),
    ((select l.id
        from language l
        where l.tag = 'lt-LT'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '2260643'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '2256041'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '2217905'),
    ((select l.id
        from language l
        where l.tag = 'ar'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '2212253'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '2212128'),
    ((select l.id
        from language l
        where l.tag = 'ar'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '2205687'),
    ((select l.id
        from language l
        where l.tag = 'ar'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '2203716'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '2197000'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ar'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '2189171'),
    ((select l.id
        from language l
        where l.tag = 'lv-LV'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '2186851'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'nl-BE'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '2183167'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '2182178'),
    ((select l.id
        from language l
        where l.tag = 'ar'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '2178405'),
    ((select l.id
        from language l
        where l.tag = 'ar'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '2167027'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Energy - Water & Utilities' and origin = 'MARKETPLACE'), '2151929'),
    ((select l.id
        from language l
        where l.tag = 'ru-RU'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '2144460'),
    ((select l.id
        from language l
        where l.tag = 'en-AU'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '2140473'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '2140160'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '2139193'),
    ((select l.id
        from language l
        where l.tag = 'id-ID'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Retail & Wholesale Distribution/E-Commerce' and origin = 'MARKETPLACE'), '2136699'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Pharmaceuticals & Biotechnology' and origin = 'MARKETPLACE'), '2133517'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '2117458'),
    ((select l.id
        from language l
        where l.tag = 'sk-SK'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '2115006'),
    ((select l.id
        from language l
        where l.tag = 'hu-HU'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '2101007'),
    ((select l.id
        from language l
        where l.tag = 'sl-SI'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '2099982'),
    ((select l.id
        from language l
        where l.tag = 'ar'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '2080849'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-TW'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '2068647'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '2055541'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '2049204'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Retail & Wholesale Distribution/E-Commerce' and origin = 'MARKETPLACE'), '2044796'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1985779'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1967593'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1965533'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-TW'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '1957788'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '1951162'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Retail & Wholesale Distribution/E-Commerce' and origin = 'MARKETPLACE'), '1936411'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1934057'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ar-EG'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1931763'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '1931009'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '1930325'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'no-NO'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '1928607'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '1923088'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Retail & Wholesale Distribution/E-Commerce' and origin = 'MARKETPLACE'), '1920102'),
    ((select l.id
        from language l
        where l.tag = 'lv-LV'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1916156'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1880181'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1876339'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'tr-TR'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '1869502'),
    ((select l.id
        from language l
        where l.tag = 'cs-CZ'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1858339'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1852968'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ar-SA'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '1795621'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1793306'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'no-NO'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '1750810'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '1727982'),
    ((select l.id
        from language l
        where l.tag = 'pl-PL'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '1718067'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '1717811'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '1685171'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1684975'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1674038'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Pharmaceuticals & Biotechnology' and origin = 'MARKETPLACE'), '1671560'),
    ((select l.id
        from language l
        where l.tag = 'ja-JP'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1670077'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '1668465'),
    ((select l.id
        from language l
        where l.tag = 'cs-CZ'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '1666062'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1660153'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '1630463'),
    ((select l.id
        from language l
        where l.tag = 'ru-RU'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Energy - Water & Utilities' and origin = 'MARKETPLACE'), '1621317'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1611678'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '1601283'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1579183'),
    ((select l.id
        from language l
        where l.tag = 'ru-RU'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1578098'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '1566370'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-CA'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '1557272'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1552286'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1542945'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '1529832'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'ar-SA'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1518785'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1508744'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '1464807'),
    ((select l.id
        from language l
        where l.tag = 'th-TH'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Retail & Wholesale Distribution/E-Commerce' and origin = 'MARKETPLACE'), '1462222'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1455450'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1437329'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1422836'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1421804'),
    ((select l.id
        from language l
        where l.tag = 'ru-RU'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '1412530'),
    ((select l.id
        from language l
        where l.tag = 'hu-HU'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1410989'),
    ((select l.id
        from language l
        where l.tag = 'ru-RU'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1397990'),
    ((select l.id
        from language l
        where l.tag = 'cs-CZ'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1390434'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1388350'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '1374544'),
    ((select l.id
        from language l
        where l.tag = 'ar'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '1370935'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'he-IL'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1367743'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'tr-TR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1360102'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '1353957'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1324066'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '1298432'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'tr-TR'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1293344'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '1245159'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '1219148'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '1210087'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1190206'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-MX'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1183717'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '1173206'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1168872'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '1148184'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-TW'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1143698'),
    ((select l.id
        from language l
        where l.tag = 'sv-SE'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1124884'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '1116365'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1115668'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1102516'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1096216'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'he-IL'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '1074472'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '1047059'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '1047026'),
    ((select l.id
        from language l
        where l.tag = 'ja-JP'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1028868'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1024534'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '1007961'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '988942'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '986317'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '959540'),
    ((select l.id
        from language l
        where l.tag = 'zh-TW'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '954223'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '942581'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'nb-NO'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '941496'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '916982'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '900381'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '893165'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '869955'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '869913'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Pharmaceuticals & Biotechnology' and origin = 'MARKETPLACE'), '865023'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '858922'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '847936'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '842294'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'uk-UA'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '841548'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ar-AE'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '825519'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '809813'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '785094'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '784470'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '776707'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '755127'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'nb-NO'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '751759'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'ar-EG'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '739624'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '722135'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'th-TH'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '708612'),
    ((select l.id
        from language l
        where l.tag = 'pt-BR'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '700727'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '697906'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '690711'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '689012'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ar-EG'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '678281'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '668516'),
    ((select l.id
        from language l
        where l.tag = 'lt-LT'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '668263'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '657451'),
    ((select l.id
        from language l
        where l.tag = 'nl-BE'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '649304'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '615550'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '612599'),
    ((select l.id
        from language l
        where l.tag = 'lt-LT'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '612044'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '602484'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '601823'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '601721'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '600555'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '596674'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-TW'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '595601'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '588241'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '588238'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ko-KR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '586367'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '583118'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '580760'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Pharmaceuticals & Biotechnology' and origin = 'MARKETPLACE'), '580549'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '580013'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '566670'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'nl-BE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '566235'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ko-KR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '566073'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '565937'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '544835'),
    ((select l.id
        from language l
        where l.tag = 'bg-BG'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '539603'),
    ((select l.id
        from language l
        where l.tag = 'ru-RU'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '536007'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '534858'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '531889'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '531560'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-TW'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '531555'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '524415'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '516364'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '513989'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '510691'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '503692'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '503539'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '498553'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '498273'),
    ((select l.id
        from language l
        where l.tag = 'ar'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '495578'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '495165'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '495019'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'nl-BE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '492105'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Pharmaceuticals & Biotechnology' and origin = 'MARKETPLACE'), '487606'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '478551'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '475294'),
    ((select l.id
        from language l
        where l.tag = 'ar'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '473211'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'ar-SA'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '470829'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-HK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '468167'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '465958'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-TW'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '464783'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '464253'),
    ((select l.id
        from language l
        where l.tag = 'es'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '462710'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Pharmaceuticals & Biotechnology' and origin = 'MARKETPLACE'), '459114'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-CA'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '456245'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '455865'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '454479'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '449036'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '446914'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '446866'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '444889'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'nl-BE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '444851'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ar'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '442395'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'vi-VN'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '442390'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '441932'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '441635'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '439850'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '438530'),
    ((select l.id
        from language l
        where l.tag = 'lt-LT'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '436440'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '430322'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-CA'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '427498'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '426133'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Pharmaceuticals & Biotechnology' and origin = 'MARKETPLACE'), '421826'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '421009'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '418464'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '417609'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '417123'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Pharmaceuticals & Biotechnology' and origin = 'MARKETPLACE'), '416140'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '414286'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '412862'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '412130'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '411084'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'nl-BE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '410667'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '407195'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '406308'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '405083'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '404816'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '404508'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '401794'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Pharmaceuticals & Biotechnology' and origin = 'MARKETPLACE'), '396786'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'ar-EG'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '391601'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '390588'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '386657'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ar-EG'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '382786'),
    ((select l.id
        from language l
        where l.tag = 'en-CA'), (select l.id
        from language l
        where l.tag = 'zh-TW'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '381970'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '377389'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Retail & Wholesale Distribution/E-Commerce' and origin = 'MARKETPLACE'), '372704'),
    ((select l.id
        from language l
        where l.tag = 'sk-SK'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '371747'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '370561'),
    ((select l.id
        from language l
        where l.tag = 'ru-RU'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '369906'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'eu-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Energy - Water & Utilities' and origin = 'MARKETPLACE'), '369201'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '365933'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '364023'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '358025'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-MX'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '354080'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '353159'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '348051'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '344440'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-MX'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '337314'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ar-EG'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Energy - Water & Utilities' and origin = 'MARKETPLACE'), '334047'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ar-SA'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '329292'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'no-NO'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '325280'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '320834'),
    ((select l.id
        from language l
        where l.tag = 'ja-JP'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '318863'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '313260'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '304037'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'th-TH'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '303001'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'tr-TR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '296891'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-CA'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '284045'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '280853'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '278513'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '268099'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '267638'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ar'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '266345'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '263015'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '261594'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '261225'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '257290'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '256145'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '253958'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'tr-TR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '253440'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '252952'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '249157'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '246095'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Retail & Wholesale Distribution/E-Commerce' and origin = 'MARKETPLACE'), '243349'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '237365'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '234820'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Retail & Wholesale Distribution/E-Commerce' and origin = 'MARKETPLACE'), '234186'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Retail & Wholesale Distribution/E-Commerce' and origin = 'MARKETPLACE'), '226726'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '226559'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ar-SA'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '224521'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '223860'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fa-IR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '223505'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '222252'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '221658'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-TW'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '221595'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '220426'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-CA'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '216892'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '215938'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '212140'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'mk-MK'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '212045'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'hr-HR'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '211979'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'no-NO'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '209693'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '208352'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '207600'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '198483'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '198458'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '196892'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '190280'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'he-IL'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '188012'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Retail & Wholesale Distribution/E-Commerce' and origin = 'MARKETPLACE'), '186212'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '184241'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'vi-VN'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '180575'),
    ((select l.id
        from language l
        where l.tag = 'nl-BE'), (select l.id
        from language l
        where l.tag = 'fr-BE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '176708'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '175335'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '174104'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '171386'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '170103'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '169419'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '166287'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '165927'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '165670'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '165584'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '164475'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '162111'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '161837'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '161299'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '159548'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'hr-HR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '159014'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '153223'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Pharmaceuticals & Biotechnology' and origin = 'MARKETPLACE'), '151920'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-MX'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '151813'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '151636'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '150701'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'no-NO'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '150153'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '149994'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'nb-NO'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '149980'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '149877'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '149041'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '148963'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '148788'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'th-TH'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '142838'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '142191'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Pharmaceuticals & Biotechnology' and origin = 'MARKETPLACE'), '142082'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '141678'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '141008'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'no-NO'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '136214'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ar-EG'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '135643'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '134195'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'tr-TR'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '134166'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '133674'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '130991'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '130852'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '129510'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '129367'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'nl-BE'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '128238'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '124454'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '123546'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '123451'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '121024'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '119906'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ar'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '118284'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '118166'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '115102'),
    ((select l.id
        from language l
        where l.tag = 'cs-CZ'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '113994'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '113231'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Retail & Wholesale Distribution/E-Commerce' and origin = 'MARKETPLACE'), '112785'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '108847'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'no-NO'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '108483'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '105866'),
    ((select l.id
        from language l
        where l.tag = 'ru-RU'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '105686'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'he-IL'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '104819'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '103904'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '102858'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '100025'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ar-EG'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '97606'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '97492'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Energy - Water & Utilities' and origin = 'MARKETPLACE'), '97418'),
    ((select l.id
        from language l
        where l.tag = 'lv-LV'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '95413'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '94358'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '92695'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '91016'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '89699'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '89669'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ar-EG'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '89352'),
    ((select l.id
        from language l
        where l.tag = 'af-ZA'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '88313'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '85937'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '85789'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ko-KR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '85537'),
    ((select l.id
        from language l
        where l.tag = 'tr-TR'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '84901'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '83831'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '83746'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'is-IS'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '83716'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '81166'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '79309'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '78021'),
    ((select l.id
        from language l
        where l.tag = 'ro-RO'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '74029'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '73836'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '73715'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '73338'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '72263'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '69996'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-TW'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '68587'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '68385'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '67549'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '67273'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '65392'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '64798'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '62833'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '61405'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '61288'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '61254'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '61219'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '60790'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'el-GR'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '59405'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '59382'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '59120'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '57976'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '57949'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '57751'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '57168'),
    ((select l.id
        from language l
        where l.tag = 'sk-SK'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '57116'),
    ((select l.id
        from language l
        where l.tag = 'ru-RU'), (select l.id
        from language l
        where l.tag = 'be-BY'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '57013'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '56774'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'uk-UA'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '56721'),
    ((select l.id
        from language l
        where l.tag = 'es'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '56533'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '56221'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '55499'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '54345'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '53303'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '52848'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '52405'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '50982'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'nb-NO'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '50680'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '50588'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '49416'),
    ((select l.id
        from language l
        where l.tag = 'en-CA'), (select l.id
        from language l
        where l.tag = 'fr-CA'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Energy - Water & Utilities' and origin = 'MARKETPLACE'), '48756'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '47932'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '47867'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '47677'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'tr-TR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '47627'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ms-MY'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '46657'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '46472'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '45542'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-MX'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '45187'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '45041'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '44954'),
    ((select l.id
        from language l
        where l.tag = 'ja-JP'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '44471'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '43924'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '43791'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '42881'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '42588'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '42532'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '42529'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'tr-TR'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '41825'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '41753'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '41399'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '41237'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '40949'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '40169'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '39276'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '39009'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Energy - Water & Utilities' and origin = 'MARKETPLACE'), '38230'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '38051'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '37901'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Pharmaceuticals & Biotechnology' and origin = 'MARKETPLACE'), '37413'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '36992'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '36436'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '36214'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '36113'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ko-KR'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '35219'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '35069'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '34128'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '33503'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '33193'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '32025'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '31665'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'en-AU'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '31505'),
    ((select l.id
        from language l
        where l.tag = 'ru-RU'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '31450'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '31205'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '31030'),
    ((select l.id
        from language l
        where l.tag = 'en-CA'), (select l.id
        from language l
        where l.tag = 'fr-CA'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '30610'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '30555'),
    ((select l.id
        from language l
        where l.tag = 'tr-TR'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '30195'),
    ((select l.id
        from language l
        where l.tag = 'el-GR'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '29907'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'hr-HR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '29401'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'be-BY'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '29398'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '28999'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'ar-SA'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '28853'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '28286'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '27884'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '27188'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'tr-TR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '26723'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Energy - Water & Utilities' and origin = 'MARKETPLACE'), '26571'),
    ((select l.id
        from language l
        where l.tag = 'pt-BR'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '25625'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '25490'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '24630'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '23995'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '23932'),
    ((select l.id
        from language l
        where l.tag = 'en-CA'), (select l.id
        from language l
        where l.tag = 'fr-CA'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '23576'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '23415'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '23287'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '23022'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '23001'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '22777'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '22775'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '22664'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '22232'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '21848'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '21678'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '21326'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '21218'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'uk-UA'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '20123'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '19568'),
    ((select l.id
        from language l
        where l.tag = 'ru-RU'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '19233'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '18797'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'hr-HR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '17347'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '17304'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '17234'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '17199'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '16923'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '16899'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '16705'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '16378'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-HK'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '15945'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'tr-TR'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '15850'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'vi-VN'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '15671'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '15333'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'pt-BR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '15181'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '15164'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '15118'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '14974'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '14266'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'no-NO'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '14244'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fi-FI'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '14228'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '14157'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '14146'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '13707'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ms-MY'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '13684'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'mk-MK'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '13629'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '13582'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '13540'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ar-EG'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '13410'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'tr-TR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '13052'),
    ((select l.id
        from language l
        where l.tag = 'ru-RU'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '12874'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'en-CA'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '12781'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '12541'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '12460'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '12354'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'ar-SA'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '12079'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '12002'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '11782'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-TW'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '11714'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '11594'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '11473'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '11374'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-CA'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '11232'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '11135'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '10875'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '10766'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '10536'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '10485'),
    ((select l.id
        from language l
        where l.tag = 'vi-VN'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '10429'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '10355'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '10103'),
    ((select l.id
        from language l
        where l.tag = 'ja-JP'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '10094'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '9959'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '9794'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '9042'),
    ((select l.id
        from language l
        where l.tag = 'ru-RU'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Energy - Water & Utilities' and origin = 'MARKETPLACE'), '8858'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '8755'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '8730'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ms-MY'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '8395'),
    ((select l.id
        from language l
        where l.tag = 'ru-RU'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '8306'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'sl-SI'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '8099'),
    ((select l.id
        from language l
        where l.tag = 'fr-CA'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '7420'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'tr-TR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '7205'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Retail & Wholesale Distribution/E-Commerce' and origin = 'MARKETPLACE'), '6648'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '6436'),
    ((select l.id
        from language l
        where l.tag = 'ru-RU'), (select l.id
        from language l
        where l.tag = 'be-BY'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '6331'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '6328'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '6208'),
    ((select l.id
        from language l
        where l.tag = 'es'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '6087'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '5821'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '5782'),
    ((select l.id
        from language l
        where l.tag = 'sv-SE'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '5381'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ar-EG'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '5300'),
    ((select l.id
        from language l
        where l.tag = 'sv-SE'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '5298'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '5097'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '5082'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '4706'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4553'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4502'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '4412'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '4389'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4280'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'sk-SK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4057'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ar-AE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3926'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '3808'),
    ((select l.id
        from language l
        where l.tag = 'eu-ES'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3764'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'tr-TR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3554'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '3488'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '3471'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '3464'),
    ((select l.id
        from language l
        where l.tag = 'en-AU'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '3432'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '3429'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'cy-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3078'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '3032'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '3021'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '2931'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '2927'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '2872'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '2697'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'bg-BG'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '2684'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Energy - Water & Utilities' and origin = 'MARKETPLACE'), '2654'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ko-KR'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '2570'),
    ((select l.id
        from language l
        where l.tag = 'pt-PT'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '2369'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '2057'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '2016'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '1921'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1813'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '1736'),
    ((select l.id
        from language l
        where l.tag = 'tr-TR'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1732'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1682'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'pl-PL'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1675'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '1668'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1652'),
    ((select l.id
        from language l
        where l.tag = 'hr-HR'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '1478'),
    ((select l.id
        from language l
        where l.tag = 'fr-BE'), (select l.id
        from language l
        where l.tag = 'nl-BE'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '1386'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1383'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '1318'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '1233'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Hospitality/Travel/Tourism' and origin = 'MARKETPLACE'), '1160'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1150'),
    ((select l.id
        from language l
        where l.tag = 'cs-CZ'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Pharmaceuticals & Biotechnology' and origin = 'MARKETPLACE'), '1117'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ko-KR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '1081'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'pt-PT'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '1068'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Energy - Water & Utilities' and origin = 'MARKETPLACE'), '1062'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '1061'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '1058'),
    ((select l.id
        from language l
        where l.tag = 'ru-RU'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '985'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'be-BY'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '961'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'et-EE'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '959'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '948'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '940'),
    ((select l.id
        from language l
        where l.tag = 'uk-UA'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Pharmaceuticals & Biotechnology' and origin = 'MARKETPLACE'), '900'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'lt-LT'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '881'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '839'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'be-BY'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '837'),
    ((select l.id
        from language l
        where l.tag = 'ro-RO'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '816'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '814'),
    ((select l.id
        from language l
        where l.tag = 'zh-CN'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '799'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ms-MY'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '784'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ja-JP'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '782'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ko-KR'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '782'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'th-TH'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '782'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'vi-VN'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '782'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'ro-RO'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '735'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'hu-HU'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '729'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Pharmaceuticals & Biotechnology' and origin = 'MARKETPLACE'), '675'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ru-RU'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Legal Services' and origin = 'MARKETPLACE'), '640'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'uk-UA'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '563'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '476'),
    ((select l.id
        from language l
        where l.tag = 'sv-SE'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '432'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'es'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '396'),
    ((select l.id
        from language l
        where l.tag = 'ja-JP'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'R&D/Patents' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '375'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'lv-LV'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '371'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'nl-NL'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '355'),
    ((select l.id
        from language l
        where l.tag = 'it-IT'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '343'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '340'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'User Interface Text' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '275'),
    ((select l.id
        from language l
        where l.tag = 'ru-RU'), (select l.id
        from language l
        where l.tag = 'de-DE'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '245'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'ar-SA'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '221'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'uk-UA'), (select ct.id
        from content_type ct
        where ct.description = 'Informative content' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Healthcare/Medical Equipment & Supplies' and origin = 'MARKETPLACE'), '130'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'be-BY'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '129'),
    ((select l.id
        from language l
        where l.tag = 'es-ES'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '125'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'be-BY'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '119'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-CA'), (select ct.id
        from content_type ct
        where ct.description = 'Online Help' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '104'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '94'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'ar-SA'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '89'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'zh-CN'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '76'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'sv-SE'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '69'),
    ((select l.id
        from language l
        where l.tag = 'nl-NL'), (select l.id
        from language l
        where l.tag = 'en-GB'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Manufacturing/Industrial Electronics' and origin = 'MARKETPLACE'), '63'),
    ((select l.id
        from language l
        where l.tag = 'fr-FR'), (select l.id
        from language l
        where l.tag = 'en-US'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '55'),
    ((select l.id
        from language l
        where l.tag = 'en-GB'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'Marketing Material' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Financial Services' and origin = 'MARKETPLACE'), '55'),
    ((select l.id
        from language l
        where l.tag = 'da-DK'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '50'),
    ((select l.id
        from language l
        where l.tag = 'de-DE'), (select l.id
        from language l
        where l.tag = 'en-CA'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '30'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-FR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '23'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'da-DK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '11'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'uk-UA'), (select ct.id
        from content_type ct
        where ct.description = 'Administrative' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Automotive' and origin = 'MARKETPLACE'), '11'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'hr-HR'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '10'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'cs-CZ'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Cloud Services & Computer Software' and origin = 'MARKETPLACE'), '8'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'mk-MK'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Undefined Sector' and origin = 'MARKETPLACE'), '4'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'es-ES'), (select ct.id
        from content_type ct
        where ct.description = 'Other' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Business Services' and origin = 'MARKETPLACE'), '3'),
    ((select l.id
        from language l
        where l.tag = 'fi-FI'), (select l.id
        from language l
        where l.tag = 'it-IT'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '3'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'tr-TR'), (select ct.id
        from content_type ct
        where ct.description = 'User Manual' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Consumer Goods / Electronics & Hardware' and origin = 'MARKETPLACE'), '2'),
    ((select l.id
        from language l
        where l.tag = 'en-US'), (select l.id
        from language l
        where l.tag = 'fr-CA'), (select ct.id
        from content_type ct
        where ct.description = 'Legal' and origin = 'MARKETPLACE'), (select d.id
        from domain d
        where d.description = 'Chemicals' and origin = 'MARKETPLACE'), '1')
ON DUPLICATE KEY
UPDATE word_count = word_count + VALUES
(word_count);