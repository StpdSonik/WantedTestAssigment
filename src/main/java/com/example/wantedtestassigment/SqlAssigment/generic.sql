-- Задача: написать запрос который выведет всех родственников (ФИО, дата рождение и родственнная связь)
-- для сотрудника с personId ='test'
SELECT pg.familyName          AS Lastname,
       pg.givenName           AS Firstname,
       pg.middleName          AS Middlename,
       pg.birthDate           AS BirthDate,
       pd.contactRelationship AS Relationship
FROM HPPersonDependant pd
         JOIN HPPersonGeneric pg ON pd.HPRelatedPersonSysId = pg.sysId
WHERE pd.HPPersonGenericSysId = (SELECT sysId FROM HPPersonGeneric WHERE personId = 'test')
