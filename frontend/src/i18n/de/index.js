export default {
  failed: "Action failed",
  success: "Action was successful",
  index: {
    activities: "Aktivitäten",
    nextLevel: "Nächstes Level",
    sending: {
      error: 'Fehler beim Abrufen der Challenges.'
    },
    scoreTips: {
      title: 'Tipps vom Guru',
      energyConsumption: {
        label: 'Energieverbrauch',
        text: 'Du hast im Büro sehr viel Energie verbraucht. Um deinen Energieverbrauch achte in Zukunft' +
          ' darauf, Geräte auszuschalten, wenn du sie nicht mehr brauchst. Dazu gehört auch der dritte Bildschirm,' +
          ' auf dem sowieso nur dein Spotify-Fenster geöffnet ist. Versuche außerdem darauf zu achten, am hellen Tag' +
          ' die Lampen auszuschalten. Auch eine Modernisierung der Arbeitsgeräte, z.B. LEDs statt Glühbirnen und' +
          ' Leuchtstoffröhren, oder energieeffizientere Geräte leisten einen großen Beitrag zu geringerem' +
          ' Energieverbrauch.'
      },
      lunch: {
        label: 'Nahrungsmittel',
        text: 'Dein Mittagessen war heute sicherlich sehr lecker. Allerdings hat die Herstellung davon sehr viel CO2' +
          ' freigesetzt. Lokale und unverarbeitete Produkte haben einen deutlich geringen CO2-Fußabdruck, als' +
          ' tierische oder verarbeitete Produkte. ' +
          '<a href="https://www.ifeu.de/fileadmin/uploads/Reinhardt-Gaertner-Wagner-2020-Oekologische-Fu%c3%9fabdruecke-von-Lebensmitteln-und-Gerichten-in-Deutschland-ifeu-2020.pdf" target="_blank">Hier</a>' +
          ' findest du eine sehr detaillierte Auflistung von Lebensmitteln und ihrem CO2-Fußabdruck.'
      },
      commute: {
        label: 'Pendelverkehr',
        text: 'Auf deinem Weg zur Arbeit und deinen dienstlichen Fahrten hast du sehr viel CO2 produziert. Hier gibt' +
          ' es ein par gute Tipps, die CO2-Produktion zu reduzieren. Je mehr Menschen in einem Fahrzeug fahren, desto' +
          ' besser. Versuche also, öfter mal öffentliche Verkehrsmittel zu nutzen. Wenn du das Fahrrad nimmst oder zu' +
          ' Fuß gehst, produzierst du natürlich noch weniger CO2.'
      },
      coffee: {
        label: 'Kaffee',
        text: 'Kaffee ist sehr lecker und hilft durch einen langen Tag im Büro. Allerdings produziert die' +
          ' Kaffeeproduktion neben Kaffee leider auch extrem viel CO2. Eine Tasse produziert bei etwa 75g CO2. Trinke' +
          ' also öfter mal eine Tasse Tee anstatt Kaffee. Mit Leitungswasser stehst du natürlich noch besser dar.'
      }
    }
  },
  dailyChallengesView: {
    title: 'Daily Challenge',
    challengeName: 'Titel',
    description: 'Beschreibung',
    points: 'Punkte',
    date: 'Datum',
    sending: {
      error: 'Fehler beim Abrufen der aktuellen Daily Challenge.'
    }
  },
  highScores: {
    title: 'Bestenliste',
    sending: {
      error: 'Fehler beim Abrufen der Bestenliste.'
    },
    table: {
      placement: 'Platz',
      name: 'Name',
      badges: 'Badges',
      co2Score: 'Score'
    }
  },
  userStatisticsForm: {
    title: "Statistik eintragen",
    coffee: {
      label: "Anzahl Kaffee"
    },
    food: {
      title: 'Lunch-Sore',
      weight: 'Gewicht (g)',
      type: 'Zutat',
      add: 'Zutat hinzufügen',
      score: 'Score'
    },
    dailyChallenge: {
      label: 'Daily Challenge geschafft'
    },
    commuteInput: {
      title: "Pendelfahrt {index} angeben",
      commuteType: {
        label: "Fortbewegungsmittel"
      },
      commuteDistance: {
        label: "Distanz"
      },
      add: 'Pendelfahrt hinzufügen'
    },
    sending: {
      success: 'Erfolgreich abgesendet.',
      error: 'Beim Absenden ist ein Fehler aufgetreten.',
      alreadySend: 'Du hast heute schon deine Werte abgeschickt.'
    }
  },
  userSettings: {
    title: "Einstellungen",
    send: "Speichern",
    cancel: "Abbrechen",
    email: {
      label: "E-Mail-Adresse"
    },
    displayName: {
      label: "Anzeigename"
    },
    seat: {
      label: "Sitzplatz"
    },
    defaultCommuteType: {
      label: "Standardfortbewegungsmittel"
    },
    defaultCommuteDistance: {
      label: "Standarddistanz"
    }
  },
  commuteType: {
    walking: "Laufen",
    bike: "Fahrrad",
    car: "Auto",
    carpool: "Fahrgemeinschaft",
    bus: "Bus",
    tram: "U-Bahn",
    train: "Zug",
    plane: "Flugzeug",
    ebike: "E-Bike",
    escooter: "E-Scooter",
    ecar: "Elektroauto",
    hybridcar: "Hybridauto"
  },
  foodType: {
    beef: 'Rind',
    pig: 'Schwein',
    chicken: 'Geflügel',
    lamb: 'Lamm',
    fish: 'Fisch',
    rice: 'Reis',
    noodles: 'Nudeln',
    fries: 'Pommes',
    butter: 'Butter',
    creme: 'Sahne',
    milk: 'Milch',
    potatoes: 'Kartoffeln',
    lentil: 'Linsen',
    oatmeal: 'Haferflocken'
  },
  login: {
    title: "Login",
    username: "Nutzername",
    password: "Passwort",
    send: "Login",
    register: "Registrieren",
    errors: {
      usernamePasswordWrong: "Nutzername oder Passwort falsch.",
      noUsernamePassword: "Nutzername und Passwort müssen angegeben werden."
    }
  },
  register: {
    title: "Registrieren",
    username: "Nutzername",
    password: "Passwort",
    repeatPassword: "Passwort wiederholen",
    send: "Registrieren",
    login: "Login"
  }
};
